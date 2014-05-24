package com.newbilius.WinCopyPast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.analytics.HitBuilders;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import javax.inject.Inject;

@SuppressWarnings("deprecation")
public class ConfigActivity extends RoboActivity {

    @InjectView(R.id.editTextLogin)
    EditText login;
    @InjectView(R.id.editTextPassword)
    EditText password;
    @InjectView(R.id.buttonChangeStatus)
    Button buttonChangeStatus;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @InjectView(R.id.textViewError)
    TextView textViewError;
    @Inject
    Options options;
    @Inject
    ConfigActivityController controller;
    @InjectView(R.id.checkBoxVibration)
    CheckBox checkBoxVibration;
    @InjectView(R.id.checkBoxSound)
    CheckBox checkBoxSound;
    @InjectView(R.id.checkBoxInstantCopy)
    CheckBox checkBoxInstantCopy;
    @InjectView(R.id.buttonHelp)
    View buttonHelp;

    @Override
    public void onStart() {
        super.onStart();
        Analytics.getTracker(this).setScreenName("CONFIG");
        Analytics.getTracker(this).send(new HitBuilders.AppViewBuilder().build());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setFields();

        buttonChangeStatus.setText(controller.GetButtonText(getBaseContext()));
        checkBoxVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                options.setVibration(isChecked);
            }
        });
        checkBoxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                options.setSound(isChecked);
            }
        });
        checkBoxInstantCopy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                options.setInstantCopy(isChecked);
            }
        });
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AboutActivity.class));
            }
        });
    }

    private void setFields(){
        login.setText(options.getLogin());
        password.setText(options.getPass());
        checkBoxVibration.setChecked(options.getVibration());
        checkBoxSound.setChecked(options.getSound());
        checkBoxInstantCopy.setChecked(options.getInstantCopy());
        SetFieldsEnabled(null);
    }

    OnComplete onComplete=new OnComplete() {

        @Override
        public void Complete() {
            progressBar.setVisibility(View.GONE);
            textViewError.setVisibility(View.GONE);
            buttonChangeStatus.setText(controller.GetButtonText(getBaseContext()));
            buttonChangeStatus.setEnabled(true);
            SetFieldsEnabled(null);
            if (controller.GetStatus()){
                Toast.makeText(getBaseContext(),R.string.config_saved,Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void Process(String text) {
            progressBar.setVisibility(View.VISIBLE);
            textViewError.setVisibility(View.GONE);
            buttonChangeStatus.setEnabled(false);
            SetFieldsEnabled(false);
        }

        @Override
        public void Error(String text) {
            progressBar.setVisibility(View.GONE);
            textViewError.setVisibility(View.VISIBLE);
            textViewError.setText(text);
            buttonChangeStatus.setEnabled(true);
            SetFieldsEnabled(null);
        }
    };

    private void SetFieldsEnabled(Boolean value){
        if (value==null){
            value=!controller.GetStatus();
        }
        login.setEnabled(value);
        password.setEnabled(value);
    }

    @SuppressWarnings("ConstantConditions")
    public void OnChangeButtonClickListener(View v) {
        controller.ChangeParams(login.getText().toString(),
                                password.getText().toString(),
                                this,
                                onComplete
        );
    }
}