package com.brewery.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class VoteActivity extends ActionBarActivity {

    private int option1, option2,option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_screen);
        Intent receivingIntent = this.getIntent();
        Bundle receivingBundle = receivingIntent.getExtras();
        Poll currentPoll = (Poll)receivingBundle.getParcelable("poll");
        int pastPosition = receivingBundle.getInt("position");
        ArrayList<Choice> choiceArrayList = receivingBundle.getParcelableArrayList("choiceList");
        for (Choice choice : choiceArrayList) {
            currentPoll.addChoice(choice);
        }
        TextView pollQuestion = (TextView)findViewById(R.id.vote_poll_question);
        pollQuestion.setText(currentPoll.getPollQuestion());
        ListView pollOptionList = (ListView)findViewById(R.id.vote_option_list);
        PollVoteAdapter pollVoteAdapter = new PollVoteAdapter(this, currentPoll, pastPosition);
        pollOptionList.setAdapter(pollVoteAdapter);
    }

    public void returnToMain(View view) {
        finish();
    }
}
