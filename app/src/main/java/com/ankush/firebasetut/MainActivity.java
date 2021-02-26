package com.ankush.firebasetut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   EditText mEmail;
   EditText mPass;
   Button Registration;
   TextView Login;
   FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mEmail=findViewById(R.id.Email);
        mPass=findViewById(R.id.Pass);
        Registration=findViewById(R.id.log);
        Login=findViewById(R.id.Login);
        mAuth=FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignInactivity.class));
            }
        });
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Createuser();
            }
        });

    }
    private void Createuser()
    {
        String Email=mEmail.getText().toString();
        String pass=mPass.getText().toString();
        if (!Email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            if (!pass.isEmpty())
            {
                mAuth.createUserWithEmailAndPassword(Email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,SignInactivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Registration Errro",Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                mPass.setError("Empty filled is not allowed");
            }
        }
        else if(Email.isEmpty())
        {
            mEmail.setError("Empty filled are not allowed");
        }
        else{
            mEmail.setError("pleas enter correct Email");
        }
    }

}