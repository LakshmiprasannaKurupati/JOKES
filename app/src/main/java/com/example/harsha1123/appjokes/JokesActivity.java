package com.example.harsha1123.appjokes;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JokesActivity extends AppCompatActivity {
RecyclerView rv;
String num,jokes;
Integer num1;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        rv=findViewById(R.id.recycler);
        context=getBaseContext();
        num=getIntent().getStringExtra("key");
        num1=Integer.parseInt(num);
        MyTask task=new MyTask();
        task.execute();
    }

    private class MyTask extends AsyncTask<String,Void,String> {
        String url="http://api.icndb.com/jokes/random/"+num;
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url1=new URL(url);
                HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
                InputStream is=connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                String line="";
                StringBuilder builder=new StringBuilder();
                while ((line=reader.readLine())!=null)
                {
                    builder.append(line+"\n");
                }
                return builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(JokesActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObject=new JSONObject(s);
                JSONArray array=jsonObject.getJSONArray("value");
                for (int i=0;i<array.length();i++)
                {
                    JSONObject index=array.getJSONObject(i);
                     jokes=index.getString("joke");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            rv.setLayoutManager(new LinearLayoutManager(JokesActivity.this));
            rv.setAdapter(new MyAdapter(JokesActivity.this,jokes,num1));
        }
    }
}
