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
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;


public class GetListOfBooksActivity extends ActionBarActivity {



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
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_list_of_books);
        String id=getIntent().getStringExtra("id");
//        TextView textView=new TextView(this);
//        textView.setTextSize(50);
//        textView.setText(message);
//        setContentView(textView);
        final Intent intent=getIntent();

        String url="http://172.20.192.102:8080/book/"+id;
        jsonParser=new JSONParser();
        try {
            response=jsonParser.doInBackground(url);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
            stream.close();
            JSONObject jsonObject=new JSONObject(stringBuilder.toString());

                title= (String) jsonObject.get("book_title");
                category= (String) jsonObject.get("category");
                book_id=(int) jsonObject.get("book_id");
                owner=(String) jsonObject.get("owner");
                status=(String) jsonObject.get("status");
                current_user= jsonObject.get("current_user") ;

            TextView textView=(TextView) findViewById(R.id.title);
            textView.setText(title);
            TextView textView1=(TextView) findViewById(R.id.category);
            textView1.setText(category);
            TextView textView2=(TextView) findViewById(R.id.book_id);
            textView2.setText(book_id);
            TextView textView3=(TextView) findViewById(R.id.owner);
            textView3.setText(owner);
            TextView textView4=(TextView) findViewById(R.id.status);
            textView4.setText(status);
            TextView textView5=(TextView) findViewById(R.id.current_user);
            textView5.setText(current_user.toString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

       final ListView lv = (ListView) findViewById(R.id.list_view);
//
//        // Adding items to listview
//        adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.product_name, products);
//        lv.setAdapter(adapter);
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                Intent intent1=new Intent(GetListOfBooksActivity.this,GetListOfBooksActivity.class);
                String itemValue = (String) lv.getItemAtPosition(position);
                intent1.putExtra("id",itemValue);
                startActivity(intent1);
            }
        };

        lv.setOnItemClickListener(mMessageClickedHandler);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_list_of_books, menu);
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
