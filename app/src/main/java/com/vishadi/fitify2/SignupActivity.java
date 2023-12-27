package com.vishadi.fitify2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;

    EditText emailSign, nameSign, ageSign, passSign, confSign;
    Button signupButton;
    TextView loginRedirectText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailSign = findViewById(R.id.EmailSign);
        nameSign = findViewById(R.id.NameSign);
        ageSign = findViewById(R.id.AgeSign);
        passSign = findViewById(R.id.PassSign);
        confSign = findViewById(R.id.PassCon);
        signupButton = findViewById(R.id.signButton);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        auth =  FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameSign.getText().toString().trim();
                String email = emailSign.getText().toString().trim();
                String age = ageSign.getText().toString().trim();
                String password = passSign.getText().toString().trim();
                String confirm = confSign.getText().toString().trim();

                if (email.isEmpty()) {
                    emailSign.setError("Email cannot be Empty");
                }
                if (name.isEmpty()) {
                    nameSign.setError("Name cannot be Empty");
                }
                if (age.isEmpty()) {
                    ageSign.setError("Age cannot be Empty");
                }
                if (password.isEmpty()) {
                    passSign.setError("Password cannot be Empty");
                }
                if (confirm.isEmpty()){
                    confSign.setError("Confirm Password cannot be Empty");
                }
                if (!password.equals(confirm)) {
                    confSign.setError("Passwords do not match");
                }
                else {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, loginActivity.class));
                            } else {
                                Toast.makeText(SignupActivity.this, "Signup Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, loginActivity.class));
            }
        });


    }
}