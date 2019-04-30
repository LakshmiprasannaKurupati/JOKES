package com.example.apsdc.jokesapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num=findViewById(R.id.edit1);
    }

    public void submit(View view) {
        if (num.getText().toString().length()>0) {
            Integer number = Integer.parseInt(num.getText().toString());
            if (number >= 1 && number <= 100) {
                Intent intent = new Intent(this, JokesActivity.class);
                intent.putExtra("key", num.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please enter the numbers from 1 to 100", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
