package com.example.quizapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Model.PersonUsersData;
import com.example.quizapp.Model.UsersData;
import com.example.quizapp.databinding.FragmentUserBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class fragment_user extends Fragment {


    FragmentUserBinding binding;
    FirebaseFirestore database;

    public fragment_user() {
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
        binding = FragmentUserBinding.inflate(inflater, container, false);

        database = FirebaseFirestore.getInstance();

        ArrayList<UsersData> users = new ArrayList<>();
        final fragment_userAdapter adapter = new fragment_userAdapter(getContext(), users);

        database.collection("User").orderBy("marks", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            UsersData user = snapshot.toObject(UsersData.class);
                            users.add(user);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        binding.recyclerView.setAdapter(adapter);


        return binding.getRoot();

    }
}