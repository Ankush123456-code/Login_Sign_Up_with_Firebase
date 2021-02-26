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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInactivity extends AppCompatActivity {
    EditText mEmail;
    EditText mPass;
    Button Registration;
    TextView Login;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_inactivity);
        getSupportActionBar().hide();
        mEmail=findViewById(R.id.email_sign);
        mPass=findViewById(R.id.Pass_sign);
        Registration=findViewById(R.id.login);
        Login=findViewById(R.id.already_registered);
        mAuth=FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInactivity.this,MainActivity.class));
            }
        });
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }
    private void LoginUser()
    {
        String Email=mEmail.getText().toString();
        String pass=mPass.getText().toString();
        if (!Email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            if (!pass.isEmpty())
            {
            mAuth.signInWithEmailAndPassword(Email,pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInactivity.this,Home.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Login Failled",Toast.LENGTH_SHORT).show();
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