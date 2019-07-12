package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.diptoroy.example.onlinequizappproject.Model.CommonModel;
import com.diptoroy.example.onlinequizappproject.Model.QuestionScore;
import com.diptoroy.example.onlinequizappproject.Model.Ranking;
import com.diptoroy.example.onlinequizappproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoneActivity extends AppCompatActivity {

    TextView txt_score,txt_get_question;
//    ProgressBar progress;

    FirebaseDatabase database;
    DatabaseReference question_score;
    //DatabaseReference rank;

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");
        //rank = database.getReference("Rank");

        txt_score = findViewById(R.id.text_Total_Score);
        txt_get_question = findViewById(R.id.text_Total_Questions);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

//        progress = findViewById(R.id.done_Progress_Bar);

//        try_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent doneQuiz = new Intent(DoneActivity.this,HomeActivity.class);
//                startActivity(doneQuiz);
//                finish();
//            }
//        });

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            txt_score.setText(String.format("SCORE : %d",score));
            txt_get_question.setText(String.format("ANSWERED : %d / %d",correctAnswer,totalQuestion));

//            progress.setMax(totalQuestion);
//            progress.setProgress(correctAnswer);

            question_score.child(String.format("%s_%s", CommonModel.currentUser.getUserName(),CommonModel.categoryId))
                    .setValue(new QuestionScore(String.format("%s_%s",CommonModel.currentUser.getUserName(),CommonModel.categoryId)
                            ,CommonModel.currentUser.getUserName()
                            ,String.valueOf(score)
                            ,CommonModel.categoryId
                            ,CommonModel.cateogryName));

//            rank.child(String.format("%s_%s", CommonModel.currentUser.getUserName(),CommonModel.categoryId))
//                    .setValue(new Ranking(String.format("%s_%s",CommonModel.currentUser.getUserName(),CommonModel.categoryId)
//                            ,CommonModel.currentUser.getUserName()
//                            ,String.valueOf(score)));



        }
    }
}
