package com.example.sivagurunathanv.search_box;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
        HttpResponse response = null;
        if(params[0].equals("http://172.20.195.191:8080/user/login")){
        HttpPost httpPost=new HttpPost(params[0]);
        HttpClient httpClient=new DefaultHttpClient();

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
        } else if (params[0].equals("http://172.20.192.102:8080/book/search"))
        {
            HttpPost httpPost=new HttpPost(params[0]);
            HttpClient httpClient=new DefaultHttpClient();

            JSONObject jsonObject=new JSONObject();
            //validate(username,password){
            //if(user.equals("siva") && pass.equals("asdf")){
            if(params[1] != null){
                try
                {
                    jsonObject.put("query",params[1]);
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
        }
        else if(params[0].equals("http://172.20.195.191:8080/user")){
            HttpPost httpPost=new HttpPost(params[0]);
            HttpClient httpClient=new DefaultHttpClient();

            JSONObject jsonObject=new JSONObject();
            //validate(username,password){
            //if(user.equals("siva") && pass.equals("asdf")){
            if(params[1] != null){
                try
                {
                    jsonObject.put("user_id",params[1]);
                    jsonObject.put("password",params[2]);
                    jsonObject.put("location",params[3]);
                    jsonObject.put("mobile_no",params[4]);
                    jsonObject.put("adresss",params[5]);
                    jsonObject.put("pincode",params[6]);

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
        }
        else if(params[0].startsWith("http://172.20.192.102:8080/book/")){
            HttpGet httpGet=new HttpGet(params[0]);
            HttpClient client = new DefaultHttpClient();

            //validate(username,password){
            //if(user.equals("siva") && pass.equals("asdf")){
            //stringEntity.setContentType("application/json");
            httpGet.addHeader("Content-Type", "application/json");
            try {
                response = client.execute(httpGet);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        return response;
    }
}
