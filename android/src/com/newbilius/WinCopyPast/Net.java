package com.newbilius.WinCopyPast;

import android.content.Context;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Net {

    public String ErrorText="";

    public boolean Execute(String login,String pass,String pushId,Context context){
        boolean result=false;

        DefaultHttpClient httpclient=new DefaultHttpClient();

        HttpPost httppost = new HttpPost(Options.URL);

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("login", login));
        nameValuePairs.add(new BasicNameValuePair("pass", pass));
        nameValuePairs.add(new BasicNameValuePair("id", pushId));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            String resultString = EntityUtils.toString(response.getEntity());
            Log.Debug("response "+resultString);
            if (!resultString.contains("ERROR")){
                result=true;
            }else{
                if (resultString.contains("USER")){
                    ErrorText=context.getString(R.string.error_not_user);
                }else{
                    ErrorText=context.getString(R.string.error_net);
                }
            }
        } catch (IOException e) {
            Log.Error(e.getLocalizedMessage());
            e.printStackTrace();
        }

        return result;
    }
}