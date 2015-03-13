package com.brewery.voteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VoteActivity extends Activity {


    private int option1, option2,option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_screen);
        Intent receivingIntent = this.getIntent();
        Bundle receivingBundle = receivingIntent.getExtras();
        Poll currentPoll = (Poll)receivingBundle.getSerializable("poll");
        TextView pollQuestion = (TextView)findViewById(R.id.vote_poll_question);
        pollQuestion.setText(currentPoll.getPollQuestion());

    }



    public void getResults(View view) {
        CharSequence text = "Option 1: " + option1 + " - Option 2 : " + option2 +
                " Option 3: " + option3;
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
