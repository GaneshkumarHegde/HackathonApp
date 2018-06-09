package com.example.windows.hackathonapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    private ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View view) {
        EditText email = (EditText) findViewById(R.id.signupemailid);
        EditText pswd = (EditText) findViewById(R.id.signuppassword);
        EditText pswd1 = (EditText) findViewById(R.id.signuprepassword);

        if(email == null)  email.setError("Required");
        else if(pswd == null ) pswd.setError("Required");
        else {
            String emailVal = email.getText().toString();
            String pswdVal = pswd.getText().toString();
            String pswdVal1 = pswd1.getText().toString();
            progressDialog.setMessage("Loading");
            progressDialog.show();

            if (!pswdVal.equals(pswdVal1)) {
                progressDialog.dismiss();
                pswd1.setError("Passwords don't match");
            } else {
                mAuth.createUserWithEmailAndPassword(emailVal.toString().trim(), pswdVal.toString().trim())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information


                                    FirebaseUser user = mAuth.getCurrentUser();
                                    update(user);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    update(null);
                                }
                            }
                        });
            }
        }
    }

    public void update(FirebaseUser user)
    {
        if(user!=null){
            progressDialog.dismiss();
            Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(this,"Account already exists",Toast.LENGTH_LONG).show();
        }
    }
}
