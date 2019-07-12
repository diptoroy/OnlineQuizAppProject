package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.diptoroy.example.onlinequizappproject.Model.CommonModel;
import com.diptoroy.example.onlinequizappproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ShowQuizActivity extends AppCompatActivity implements View.OnClickListener {
    final static long INTERVAL = 1000; // 1 detik
    final static long TIMEOUT = 10000; // 7 detik
    int progressValue = 0;

    CountDownTimer countDown;
    int index = 0, score = 0, thisQuestion = 0, totalQuestion, correctAnswer;


//    // Firebase
//    FirebaseDatabase database;
//    DatabaseReference reference;

    TextView txt_Score,txt_Question_Num,question_Text;
    Button btn_AnswerA,btn_AnswerB,btn_AnswerC,btn_AnswerD;
    //ImageView question_Image;
    ProgressBar progress_Bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz);


        txt_Score = findViewById(R.id.txt_Score);
        txt_Question_Num = findViewById(R.id.txt_Total_Question);
        question_Text = findViewById(R.id.question_text);

       // question_Image = findViewById(R.id.question_image);
        progress_Bar = findViewById(R.id.progress_Bar);

//        // Firebase
//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference("Question");

        btn_AnswerA = findViewById(R.id.btn_AnswerA);
        btn_AnswerB = findViewById(R.id.btn_AnswerB);
        btn_AnswerC = findViewById(R.id.btn_AnswerC);
        btn_AnswerD = findViewById(R.id.btn_AnswerD);

        btn_AnswerA.setOnClickListener(this);
        btn_AnswerB.setOnClickListener(this);
        btn_AnswerC.setOnClickListener(this);
        btn_AnswerD.setOnClickListener(this);

    }

    public void onClick(View view) {

        countDown.cancel();
        if (index < totalQuestion)
        {
            Button clickedView = (Button)view;
            if (clickedView.getText().equals(CommonModel.questionList.get(index).getAnswer())){
                score+=5;
                correctAnswer++;
                showQuestion(++index); //next question
            }
            else //choose wrong answer
            {
                showQuestion(++index);
            }

            txt_Score.setText(String.format("%d",score));
        }
    }

    private void showQuestion(int index) {
        if (index < totalQuestion)
        {
            thisQuestion++;
            txt_Question_Num.setText(String.format("%d / %d",thisQuestion,totalQuestion));
            progress_Bar.setProgress(0);
            progressValue=0;

//            if (CommonModel.questionList.get(index).getIsImageQuestion().equals("true"))
//            {
//                // If is image
//                Picasso.get()
//                        .load(CommonModel.questionList.get(index).getQuestion())
//                        .into(question_Image);
//
//                question_Image.setVisibility(View.VISIBLE);
//                question_Text.setVisibility(View.INVISIBLE);
//            }
//            else
//            {
//                question_Text.setText(CommonModel.questionList.get(index).getQuestion());
//
//                question_Image.setVisibility(View.INVISIBLE);
//                question_Text.setVisibility(View.VISIBLE);
//            }
            question_Text.setText(CommonModel.questionList.get(index).getQuestion());

            btn_AnswerA.setText(CommonModel.questionList.get(index).getOption1());
            btn_AnswerB.setText(CommonModel.questionList.get(index).getOption2());
            btn_AnswerC.setText(CommonModel.questionList.get(index).getOption3());
            btn_AnswerD.setText(CommonModel.questionList.get(index).getOption4());

            countDown.start();
        }
        else
        {
            // If it is final question
            Intent intent = new Intent(this,DoneActivity.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion = CommonModel.questionList.size();

        countDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long l) {
                progress_Bar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                countDown.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }


}
