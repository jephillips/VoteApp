package com.brewery.voteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Josh on 3/9/2015.
 */
public class ManagerActivity extends Activity {

    ArrayList<Poll> pollList = new ArrayList<Poll>();
    PollBuilder pollBuilder = new PollBuilder();
    PollListAdapter pollListAdapter;
    private static int rowPosition;

    public static void setRowPosition(int position) {
        rowPosition = position;
    }

    private static final int NEW_POLL_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_manager);
        ListView pollListView = (ListView) findViewById(R.id.pollListView);
        pollListAdapter = new PollListAdapter(this, pollList);
        pollListView.setAdapter(pollListAdapter);

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
        pollListAdapter.updatePollArray(pollList);

        }

    class ViewHolder {
        TextView pollNameView = null;
    }




}

