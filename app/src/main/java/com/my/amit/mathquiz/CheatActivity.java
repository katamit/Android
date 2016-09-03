package com.my.amit.mathquiz;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private final String TAG ="CheatActivity";
    private static final String ANSWER_IS_TRUE="com.my.amit.mathquiz.answer_is_true";
    private static final String IS_CHEATED = "IS_CHEATED";
    public boolean ischeated = false;

    private TextView mCheatAnswerTextView;
    private Button  mShowCheatButton;

    public static Intent newIntent(Context context, boolean b){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(ANSWER_IS_TRUE, b);
        return  intent;

    }

    public static boolean wasCheatShown(Intent i){
        return  i.getBooleanExtra(IS_CHEATED, false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG, "Inside onCreate");
        setContentView(R.layout.activity_cheat);
        final boolean ischeated = getIntent().getBooleanExtra(ANSWER_IS_TRUE,false);
        Log.d(TAG,"Reecived intent value: "+ ischeated);

        mCheatAnswerTextView = (TextView) findViewById(R.id.cheatAnswer_text_view);
        mShowCheatButton = (Button) findViewById(R.id.show_cheat_button);
        mShowCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "inside show cheat button");
                if (ischeated){
                    mCheatAnswerTextView.setText(R.string.true_text);
                }
                else {
                    mCheatAnswerTextView.setText(R.string.false_toast);
                }
                setAnswerResult(true);
            }

        });
    }


    private void setAnswerResult(boolean b){
        Intent i = new Intent();
        i.putExtra(IS_CHEATED, b);
        setResult(RESULT_OK, i);

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
}
