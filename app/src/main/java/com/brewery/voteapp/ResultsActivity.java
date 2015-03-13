package com.brewery.voteapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Josh on 3/9/2015.
 */
public class ResultsActivity extends Activity {

    private Button returnToManagerButton;
    private ListView listView;
    private ArrayList<String> resultsArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_results);
        returnToManagerButton = (Button) findViewById(R.id.returnToManagerButton);
        Intent receivingIntent = this.getIntent();
        Bundle receivingBundle = receivingIntent.getExtras();
        Poll currentPoll = (Poll)receivingBundle.getSerializable("poll");
        TextView pollNameTextView = (TextView)findViewById(R.id.poll_name_text_view);
        ListView pollResultsListView = (ListView) findViewById(R.id.poll_results_list_view);
        PollAdapter pollAdapter = new PollAdapter(this, currentPoll);
        pollResultsListView.setAdapter(pollAdapter);


        pollNameTextView.setText(currentPoll.getPollQuestion());


        // Populate the results ArrayList with data from the Poll object being passed along with the
        // intent
    }

    public void returnToManager(View view) {

        finish();

    }
}
