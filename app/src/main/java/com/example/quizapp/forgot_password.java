package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityForgotPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    TextInputLayout etEmail;
    Button resetPasswordBtn;
    String email;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.fpEmail);
        resetPasswordBtn = findViewById(R.id.button_resetPassword);
        //        Get Firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getEditText().getText().toString().trim();
                if(CheckAllFields()) {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(), login_page.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Password Reset Email has been Sent.", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private boolean CheckAllFields() {
        email = etEmail.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())  {
            etEmail.setError("Enter Correct Email");
            return false;
        }
        progressDialog.setMessage("Email Sending to your Gmail Account");
        progressDialog.setTitle("Sending...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        return true;
        // after all validation return true.
    }

}