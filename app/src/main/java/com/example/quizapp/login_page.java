package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class login_page extends AppCompatActivity {


    FirebaseFirestore firestore;
    // one buttons

    FirebaseAuth firebaseAuth;

    Button bProceed;

    // four text fields
    TextInputLayout etPassword, etEmail;

    // one boolean variable to check whether all the text fields
    // are filled by the user, properly or not.
    boolean isAllFieldsChecked = false;

    String email, password;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // register buttons with their proper IDs.
        bProceed = findViewById(R.id.Button_lgn);

        // register all the EditText fields with their IDs.
        etPassword = findViewById(R.id.password);
        etEmail = findViewById(R.id.email);
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new  ProgressDialog(this);
        bProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckAllFields();

                if(isAllFieldsChecked) {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Intent myIntent = new Intent(view.getContext(), activity_home_page.class);
                                startActivity(myIntent);
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        TextView registerView = findViewById(R.id.text_view_register);

        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), activity_register_page.class);
                startActivity(intent);
            }
        });

        TextView forgotPasswordView = findViewById(R.id.text_view_forget_password);

        forgotPasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), forgot_password.class);
                startActivity(intent);
            }
        });

//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//
//        if(currentUser != null){
//            Intent myIntent = new Intent(getApplicationContext(), activity_home_page.class);
//            startActivity(myIntent);
//        }

    }

    private boolean CheckAllFields() {

        email = etEmail.getEditText().getText().toString().trim();
        password = etPassword.getEditText().getText().toString().trim();


        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())  {
            etEmail.setError("Enter Correct Email");
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            return false;
        }
        if (password.length() < 8) {
            etPassword.setError("Password must be minimum 8 characters");
            return false;
        }
        progressDialog.setMessage("Please Wait While Login into Account....");
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
            return true;
        // after all validation return true.
    }

}