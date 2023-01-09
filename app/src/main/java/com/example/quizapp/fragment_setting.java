package com.example.quizapp;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.databinding.FragmentSettingBinding;
import com.google.firebase.auth.FirebaseAuth;

public class fragment_setting extends Fragment {


    FragmentSettingBinding binding;
    FirebaseAuth auth;
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
        binding.signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getContext(), login_page.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}