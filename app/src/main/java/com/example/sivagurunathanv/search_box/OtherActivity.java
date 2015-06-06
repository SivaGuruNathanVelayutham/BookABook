package com.example.sivagurunathanv.search_box;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;


import org.apache.http.HttpResponse;
import org.apache.http.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OtherActivity extends ActionBarActivity {


    JSONParser jsonParser;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    HttpResponse response=null;

    // Search EditText
    EditText inputSearch;
    StringBuilder stringBuilder = new StringBuilder();
    String title;
    String category;
    String owner;
    String status;
    Object current_user;
    int book_id,count;
    String [] products  = null;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        final Intent intent=getIntent();

        String query=intent.getStringExtra("search");
        String url="http://172.20.192.102:8080/book/search";
        jsonParser=new JSONParser();
        try {
            response=jsonParser.doInBackground(url,query);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
            stream.close();
            JSONObject jsonObject=new JSONObject(stringBuilder.toString());
             count= (int) jsonObject.get("count");
            products =new String[count];
            for(int i=0;i<count;i++)
            {
                title= (String) ((JSONArray)jsonObject.get("books")).getJSONObject(i).get("book_title");
                category= (String) ((JSONArray)jsonObject.get("books")).getJSONObject(i).get("category");
                book_id=(int) ((JSONArray)jsonObject.get("books")).getJSONObject(i).get("book_id");
                owner=(String) ((JSONArray)jsonObject.get("books")).getJSONObject(i).get("owner");
                status=(String) ((JSONArray)jsonObject.get("books")).getJSONObject(i).get("status");
                current_user= ((JSONArray)jsonObject.get("books")).getJSONObject(i).get("current_user") ;
                products[i]=title;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        final ListView lv = (ListView) findViewById(R.id.list_view);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.product_name, products);
        lv.setAdapter(adapter);
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                Intent intent1=new Intent(OtherActivity.this,GetListOfBooksActivity.class);
                String itemValue = (String) lv.getItemAtPosition(position);
                intent1.putExtra("id",itemValue);
                startActivity(intent1);
            }
        };

        lv.setOnItemClickListener(mMessageClickedHandler);

//
//        TextView textView=new TextView(this);
//        textView.setTextSize(query.length());
//        textView.setText(query);
//        setContentView(textView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other, menu);
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
}
