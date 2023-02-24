package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityResultBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultActivity extends AppCompatActivity {

    int POINTS = 10;
    FirebaseAuth auth;
    Button restartBtnView;
    TextView quizScore;
    int points;
    FirebaseFirestore database;
    int totalQuestions, correctAnswers;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        correctAnswers = getIntent().getIntExtra("correct", 0);
        totalQuestions = getIntent().getIntExtra("total", 0);

        auth = FirebaseAuth.getInstance();
        points = correctAnswers;
        quizScore = findViewById(R.id.score);

        restartBtnView = findViewById(R.id.restartBtn);

        quizScore.setText(String.format("%d/%d", correctAnswers, totalQuestions));

        database = FirebaseFirestore.getInstance();
        uid = auth.getCurrentUser().getUid();
        database.collection("User").document(uid).
                update("marks",points).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });

        restartBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_home_page.class);
                startActivity(intent);
            }
        });
    }
}