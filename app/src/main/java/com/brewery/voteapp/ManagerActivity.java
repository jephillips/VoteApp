package com.brewery.voteapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        ListView pollListView = (ListView) findViewById(R.id.pollListView);
        PollListAdapter pollListAdapter = new PollListAdapter();
        pollListView.setAdapter(pollListAdapter);

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

    class PollListAdapter extends ArrayAdapter<Poll> {

        PollListAdapter() {
            super(ManagerActivity.this, R.layout.poll_list_view_layout, R.id.pollListView,
                    pollList);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            

            return row;
        }
    }


}

