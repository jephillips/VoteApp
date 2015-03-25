package com.brewery.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;


/**
 * Created by Josh on 3/9/2015.
 */
public class ManagerActivity extends ActionBarActivity {

    ArrayList<Poll> pollList = new ArrayList<Poll>();
    PollBuilder pollBuilder = new PollBuilder();
    PollListAdapter pollListAdapter;
    private static int rowPosition;
    ListView pollListView;


    public static void setRowPosition(int position) {
        rowPosition = position;
    }
    private static final int NEW_POLL_REQUEST = 1;
    private static final int UPDATED_POLL = 2;
    private File pollFile;
    private PollSaver pollSaver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_manager);
        pollSaver = new PollSaver(this);
        pollList = pollSaver.loadPolls();
        ListView pollListView = (ListView) findViewById(R.id.pollListView);
        pollListAdapter = new PollListAdapter(this, pollList);
        pollListView.setAdapter(pollListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return(true);
            case R.id.about:
                Intent aboutIntent = new Intent(this, AboutScreen.class);
                startActivity(aboutIntent);
                return(true);
            case R.id.help:
                Intent helpIntent = new Intent(this, HelpScreen.class);
                startActivity(helpIntent);
                return(true);
            case R.id.action_add_poll:
                Intent newPollIntent = new Intent(this, EditorActivity.class);
                startActivityForResult(newPollIntent, NEW_POLL_REQUEST);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_POLL_REQUEST) {

            if(resultCode == RESULT_OK) {
                ArrayList<String> pollOptions = data.getStringArrayListExtra("pollOptions");
                Poll newPoll = pollBuilder.buildPoll(pollOptions);
                pollList.add(newPoll);
                pollSaver.savePolls(pollList);
                pollListAdapter.updatePollArray(pollList);
            }
            else { /* do nothing */ }

        } else if (requestCode == UPDATED_POLL) {
            if (resultCode == RESULT_OK) {
                Bundle receivedBundle = data.getBundleExtra("newBundle");
                Poll updatedPoll = (Poll) receivedBundle.getParcelable("poll");
                int oldPoll = receivedBundle.getInt("pastPosition");
                ArrayList<Choice> choiceArrayList = receivedBundle.getParcelableArrayList("choiceList");
                for (Choice choice : choiceArrayList) {
                    updatedPoll.addChoice(choice);
                }
                pollList.add(updatedPoll);
                pollList.remove(oldPoll);
                pollSaver.savePolls(pollList);
                pollListAdapter.updatePollArray(pollList);
            } else { /* do nothing */ }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (pollList != null) {
            outState.putParcelableArrayList("polls", pollList);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        pollList=state.getParcelableArrayList("polls");
        pollListAdapter.updatePollArray(pollList);
    }

}

