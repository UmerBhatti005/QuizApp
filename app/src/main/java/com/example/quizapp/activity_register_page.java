package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Model.UsersData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class activity_register_page extends AppCompatActivity {

    FirebaseFirestore firestore;
    // one buttons

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Button bProceed;

    // four text fields
    EditText etUsername, etPassword, etEmail, etMobile;
    String userName, email, mobileNumber;


    // one boolean variable to check whether all the text fields
    // are filled by the user, properly or not.
    boolean isAllFieldsChecked = false;

    String emailvalidation = "/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // register buttons with their proper IDs.
        bProceed = findViewById(R.id.Button_reg);

        // register all the EditText fields with their IDs.
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etEmail = findViewById(R.id.email);
        etMobile = findViewById(R.id.mobile);
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        progressDialog = new  ProgressDialog(this);

        bProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckAllFields();

                if(isAllFieldsChecked) {

                    final UsersData data = new UsersData(userName, email, mobileNumber);

                    firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                String uid = task.getResult().getUser().getUid();

                                firestore.collection("User").document(uid).set(data)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                                    Intent myIntent = new Intent(view.getContext(), login_page.class);
                                                    firestore = FirebaseFirestore.getInstance();
                                                    startActivity(myIntent);
                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                                            }
                                        }) ;
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

//                    Map<String, Object> user = new HashMap<>();
//                    user.put("Username", etUsername.getText().toString());
//                    user.put("Password", etPassword.getText().toString());
//                    user.put("Email", etEmail.getText().toString());
//                    user.put("Mobile", etMobile.getText().toString());
//                    firestore.collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
//                        }
//                    });
                }
            }
        });



         TextView hvAccount = findViewById(R.id.text_view_login);

         hvAccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), login_page.class );
                 startActivity(intent);
             }
         });
    }


    private boolean CheckAllFields() {
    userName = etUsername.getText().toString().trim();
    email = etEmail.getText().toString().trim();
    mobileNumber = etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            etUsername.setError("This field is required");
            return false;
        }
        if (etPassword.length() == 0) {
            etPassword.setError("Password is required");
            return false;
        }
        if (etPassword.length() < 8) {
            etPassword.setError("Password must be minimum 8 characters");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())  {
            etEmail.setError("Enter Correct Email");
            return false;
        }
        if (TextUtils.isEmpty(mobileNumber)) {
            etMobile.setError("This field is required");
            return false;
        }
        progressDialog.setMessage("Please Wait While Registration....");
        progressDialog.setTitle("Registration");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        return true;
        // after all validation return true.
    }

}