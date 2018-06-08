package com.example.windows.hackathonapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.signuptxt);

          t.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent signuppage=new Intent(MainActivity.this,Signup.class);
              startActivity(signuppage);
          }
      });
    }
}
