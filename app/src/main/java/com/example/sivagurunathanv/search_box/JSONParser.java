package com.example.sivagurunathanv.search_box;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.ResponseCache;

/**
 * Created by sivagurunathan.v on 06/06/15.
 */
public class JSONParser extends AsyncTask<String,HttpResponse,HttpResponse> {
    @Override
    protected HttpResponse doInBackground(String... params) {
        HttpPost httpPost=new HttpPost(params[0]);
        HttpClient httpClient=new DefaultHttpClient();
        HttpResponse response = null;
        JSONObject jsonObject=new JSONObject();
        //validate(username,password){
        //if(user.equals("siva") && pass.equals("asdf")){
        if(params[1] != null  && params[2] != null){
            try
            {
                jsonObject.put("user_id",params[1]);
                jsonObject.put("password", params[2]);
                StringEntity stringEntity=new StringEntity(jsonObject.toString());
                //stringEntity.setContentType("application/json");
                httpPost.addHeader("Content-Type", "application/json");
                httpPost.setEntity(stringEntity);
                try {
                    response = httpClient.execute(httpPost);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}
