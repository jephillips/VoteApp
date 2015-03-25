package com.brewery.voteapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Josh on 3/9/2015.
 */
public class ResultsActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_results);
        Intent receivingIntent = this.getIntent();
        Bundle receivingBundle = receivingIntent.getExtras();
        Poll currentPoll = receivingBundle.getParcelable("poll");
        ArrayList<Choice> choiceArrayList = receivingBundle.getParcelableArrayList("choiceList");
        for (Choice choice : choiceArrayList) {
            currentPoll .addChoice(choice);
        }
        TextView pollNameTextView = (TextView)findViewById(R.id.poll_name_text_view);
        ListView pollResultsListView = (ListView) findViewById(R.id.poll_results_list_view);
        pollResultsListView.setEnabled(false);
        PollResultAdapter pollResultAdapter = new PollResultAdapter(this, currentPoll);
        pollResultsListView.setAdapter(pollResultAdapter);
        pollNameTextView.setText(currentPoll.getPollQuestion());

    }

    public void returnToManager(View view) {

        finish();

    }
}
