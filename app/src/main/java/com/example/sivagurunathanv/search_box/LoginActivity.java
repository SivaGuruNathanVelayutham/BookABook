package com.example.sivagurunathanv.search_box;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends ActionBarActivity {

    private JSONParser jsonParser;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Button submit = (Button) findViewById(R.id.btnLogin);
        TextView textView = (TextView) findViewById(R.id.link_to_register);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
//        if (saveLogin == true) {
//            editTextUsername.setText(loginPreferences.getString("username", ""));
//            editTextPassword.setText(loginPreferences.getString("password", ""));
//            saveLoginCheckBox.setChecked(true);
//        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateUser(v);
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ValidateUser(View view) {
        Integer status=0;
        jsonParser=new JSONParser();
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        String user = username.getText().toString().toLowerCase();
        String pass = password.getText().toString();
        String url = "http://172.20.195.191:8080/user/login";
        HttpResponse response = null;
        try{
        if (user != null && pass != null) {
            response = jsonParser.doInBackground(url, user, pass);
        }
        if (response.getStatusLine().getStatusCode() == 200) {
            Intent intent = new Intent(this, SearchableActivity.class);
            startActivity(intent);
        } else if (response.getStatusLine().getStatusCode() >= 400 && response.getStatusLine().getStatusCode() < 500) {
            //Toast to handle 400 invalid username and password
        } else {
            //Toast something went wrong 500 error
        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
