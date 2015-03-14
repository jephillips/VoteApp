package com.brewery.voteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
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
        int pastPosition = receivingBundle.getInt("position");
        TextView pollQuestion = (TextView)findViewById(R.id.vote_poll_question);
        pollQuestion.setText(currentPoll.getPollQuestion());
        ListView pollOptionList = (ListView)findViewById(R.id.vote_option_list);
        PollVoteAdapter pollVoteAdapter = new PollVoteAdapter(this, currentPoll, pastPosition);
        pollOptionList.setAdapter(pollVoteAdapter);
    }
}
