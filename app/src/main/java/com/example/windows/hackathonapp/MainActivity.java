package com.example.windows.hackathonapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

     TextView t;

     ProgressDialog progressDialog1;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        progressDialog1 = new ProgressDialog(this);

        t = (TextView) findViewById(R.id.signuptxt);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signuppage=new Intent(MainActivity.this,Signup.class);
                startActivity(signuppage);
            }
        });
    }

    public void signIn(View view){

        EditText email = (EditText)findViewById(R.id.emailid);
        String emailid = email.getText().toString();

        EditText password = (EditText)findViewById(R.id.password);
        String pswd = password.getText().toString();

        progressDialog1.setMessage("Loading...");
        progressDialog1.show();

        mAuth.signInWithEmailAndPassword(emailid.toString(), pswd.toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateLogin(user);
                            progressDialog1.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            progressDialog1.dismiss();
                            updateLogin(null);
                        }

                        // ...
                    }
                });


    }
    private void updateLogin(FirebaseUser user)
    {
        if(user!=null){
            //progressDialog1.dismiss();
            //Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            //startActivity(intent);
            Toast.makeText(this,"Logged in",Toast.LENGTH_LONG).show();
        }
        else
        {
            //progressDialog1.dismiss();
            Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();

        }
    }

}
