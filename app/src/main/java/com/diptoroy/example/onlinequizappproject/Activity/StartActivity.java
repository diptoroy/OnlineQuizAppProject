package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.diptoroy.example.onlinequizappproject.Model.CommonModel;
import com.diptoroy.example.onlinequizappproject.Model.Question;
import com.diptoroy.example.onlinequizappproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static com.diptoroy.example.onlinequizappproject.Model.CommonModel.categoryId;

public class StartActivity extends AppCompatActivity {

    Button btn_play;
    TextView timer;
    TextView text1,text2,text3;

    FirebaseDatabase database;
    DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btn_play = findViewById(R.id.btn_Play);

        text1 = findViewById(R.id.rules1);
        text2 = findViewById(R.id.rules2);
        text3 = findViewById(R.id.rules3);


        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Question");

        loadQuestion(categoryId);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent show = new Intent(StartActivity.this,ShowQuizActivity.class);
                startActivity(show);
                finish();
            }
        });

//        timer = findViewById(R.id.timer);
//        CountDownTimer cTimer = new CountDownTimer(5000,10) {
//            @Override
//            public void onTick(long l) {
//
//                timer.setText("seconds remaining: " + l / 1000);
//
//            }
//
//            @Override
//            public void onFinish() {
//
//                startQuiz();
//            }
//        };
//
//        cTimer.start();

    }

//    private void startQuiz() {
//        Intent show = new Intent(StartActivity.this,ShowQuizActivity.class);
//        startActivity(show);
//        finish();
//    }

    private void loadQuestion(String categoryId) {

        if (CommonModel.questionList.size() > 0)
            CommonModel.questionList.clear();

        questions.orderByChild("categoryID").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                            Question ques = postSnapshot.getValue(Question.class);
                            CommonModel.questionList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        Collections.shuffle(CommonModel.questionList);
    }
}
