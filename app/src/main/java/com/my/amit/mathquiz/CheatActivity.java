package com.my.amit.mathquiz;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;

public class CheatActivity extends AppCompatActivity {

    private final String TAG ="CheatActivity";
    private static final String CHEAT_KEY="CHEAT_INDEX";
    private static final String IS_CHEATED = "IS_CHEATED";
    public boolean ischeated = false;

    public static Intent newIntent(Context context, int i){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(CHEAT_KEY, i);
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
        int index = getIntent().getIntExtra(CHEAT_KEY, -999);
        Log.d(TAG,"Reecived intent value: "+ index);
        if(index >= 0){
            ischeated= true;
        }
        setAnswerResult(ischeated);
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
