package com.newbilius.WinCopyPast;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.analytics.HitBuilders;
import roboguice.activity.RoboActivity;

public class AboutActivity extends RoboActivity implements View.OnClickListener {

    TextView email,site;
    Button Bemail,Bsite,buttonPrograms;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        email=(TextView)findViewById(R.id.textEmail);
        Bemail=(Button)findViewById(R.id.buttonEmail);
        Bsite=(Button)findViewById(R.id.buttonSite);
        site=(TextView)findViewById(R.id.textSite);
        buttonPrograms=(Button)findViewById(R.id.buttonPrograms);

        site.setOnClickListener(this);
        email.setOnClickListener(this);
        Bsite.setOnClickListener(this);
        Bemail.setOnClickListener(this);
        buttonPrograms.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Analytics.getTracker(this).setScreenName("ABOUT");
        Analytics.getTracker(this).send(new HitBuilders.AppViewBuilder().build());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textEmail:
            case R.id.buttonEmail:
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); // отправляем письмо
                emailIntent.setType("plain/text"); // устанавливаем тип содержимого
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"newbilius@gmail.com"}); // устанавливаем получателя
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Cloud CopyPaster"); // тема сообщения
                startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.write_email))); // заголовок окна
                break;
            case R.id.textSite:
            case R.id.buttonSite:
                Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.siteszone.ru"));
                startActivity(browseIntent);
                break;
            case R.id.buttonPrograms:
                Intent browseIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=%D0%94%D0%BC%D0%B8%D1%82%D1%80%D0%B8%D0%B9+%D0%9C%D0%BE%D0%B8%D1%81%D0%B5%D0%B5%D0%B2"));
                startActivity(browseIntent2);
                break;
        }
    }
}
