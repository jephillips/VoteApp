package com.brewery.voteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Josh on 3/9/2015.
 */
public class ManagerActivity extends Activity {

    ArrayList<Poll> pollList = new ArrayList<Poll>();
    PollBuilder pollBuilder = new PollBuilder();

    private static final int NEW_POLL_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_manager);

    }

    public void viewPoll(View view) {
        Intent viewPollIntent = new Intent(this, ResultsActivity.class);
        viewPollIntent.putExtra("pollToView", pollList.get(0));
        startActivity(viewPollIntent);
    }

    public void goVoteButton(View view) {
        Intent voteIntent = new Intent(this, VoteActivity.class);
        startActivity(voteIntent);
    }

    public void newPollButton(View view) {
        Intent newPollIntent = new Intent(this, EditorActivity.class);
        startActivityForResult(newPollIntent, NEW_POLL_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<String> pollOptions = data.getStringArrayListExtra("pollOptions");
        Poll newPoll = pollBuilder.buildPoll(pollOptions);
        pollList.add(newPoll);

        System.out.println(newPoll.getPollName());
        System.out.println(newPoll.getChoice(0));

        }

    }

