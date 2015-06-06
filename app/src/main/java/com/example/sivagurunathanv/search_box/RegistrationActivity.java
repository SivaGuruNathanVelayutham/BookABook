package com.example.sivagurunathanv.search_box;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegistrationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button register=(Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    private void Register(View v)
    {
        EditText full_name= (EditText) findViewById(R.id.reg_fullname);
        EditText email= (EditText) findViewById(R.id.reg_email);
        EditText pass=(EditText) findViewById(R.id.reg_password);
        EditText address=(EditText) findViewById(R.id.address);
        EditText location=(EditText) findViewById(R.id.locality);
        EditText pincode=(EditText) findViewById(R.id.pincode);

      //  if(full_name != null && email !=null && pass != null && address != null && location != null && pincode != null) {
            Intent intent = new Intent(this, SearchableActivity.class);
            setIntent(intent);
      //  }

    }
}
