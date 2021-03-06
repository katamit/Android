package com.my.amit.mathquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//Import the android.util.Log class for logiing
import android.util.Log;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextQuestion;
    private Button mPreviousQuestion;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private boolean mCheated;

//    definding the tag contant below to start the logging

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "Index";
    private static final int REQUEST_CODE_CHEAT = 0;


    private QuestionBank[] myQuestionBank = new QuestionBank[]{
            new QuestionBank(R.string.question_1, true),
            new QuestionBank(R.string.question_2, true),
            new QuestionBank(R.string.question_3, true),
            new QuestionBank(R.string.question_4, true)
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Inside onCreate");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text);

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(true);

            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                checkAnswer(false);

            }
        });

        mNextQuestion = (Button) findViewById(R.id.next_button);
        mNextQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex +1)% myQuestionBank.length;
                mCheated = false;
                updateQuestion();

            }

        });

        mPreviousQuestion = (Button) findViewById(R.id.previous_button);
        mPreviousQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (currentIndex > 0) {
                    currentIndex = (currentIndex - 1) % myQuestionBank.length;
                }
                else {
                    currentIndex = myQuestionBank.length -1;
                }
                mCheated = false;
                updateQuestion();

            }

        });


        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void  onClick(View view){
                Log.d(TAG,"cheat button pressed");
//                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
//                startActivity(i);

                boolean b = myQuestionBank[currentIndex].isTrueQuestion();
                Intent i = CheatActivity.newIntent(QuizActivity.this, b);
//                startActivity(i);
                startActivityForResult(i,REQUEST_CODE_CHEAT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            mCheated = CheatActivity.wasCheatShown(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Inside onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Inside onResume");
        Log.d(TAG, "Did user cheat " + mCheated);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Inside onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Inside onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Inside onStop");

    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {


        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"indised onSaveInstaneState method");

        savedInstanceState.putInt(KEY_INDEX, currentIndex);


    }

    private void updateQuestion(){
        int question = myQuestionBank[currentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }


    private void checkAnswer(boolean userPressedTrue){
        boolean answer = myQuestionBank[currentIndex].isTrueQuestion();

        int messageId = 0;

        if (mCheated){
            messageId = R.string.cheat_toast;
        }
        else if(answer == userPressedTrue){
            messageId = R.string.true_toast;

        }
        else{
            messageId = R.string.false_toast;
        }

        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();


    }
}
