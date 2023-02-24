package com.example.quizapp;

import android.animation.AnimatorInflater;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapp.Model.UsersData;
import com.example.quizapp.databinding.FragmentSettingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class fragment_setting extends Fragment {


    FragmentSettingBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String username, email, mobileNumber;
    Button etUpdate;
    ProgressDialog progressDialog;
    public fragment_setting() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        binding.signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getContext(), login_page.class);
                startActivity(intent);
            }
        });


        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckAllFields()) {
                    final UsersData data = new UsersData(username, email, mobileNumber);
                    String uid = auth.getCurrentUser().getUid();


                    firestore.collection("User").document(uid)
                            .set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
//                                    Toast.makeText(get, "Update SuccessFully", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
        return binding.getRoot();
    }

    private boolean CheckAllFields() {
        username =  binding.etUsername.getEditText().getText().toString().trim();
        email = binding.etEmail.getEditText().getText().toString().trim();
        mobileNumber = binding.etMobileNumber.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            binding.etUsername.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            binding.etEmail.setError("Email is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())  {
            binding.etEmail.setError("Enter Correct Email");
            return false;
        }
        if (TextUtils.isEmpty(mobileNumber)) {
            binding.etMobileNumber.setError("This field is required");
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