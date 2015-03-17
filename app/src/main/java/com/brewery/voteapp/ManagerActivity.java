package com.brewery.voteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Created by Josh on 3/9/2015.
 */
public class ManagerActivity extends Activity {

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_manager);
        ListView pollListView = (ListView) findViewById(R.id.pollListView);
        pollListAdapter = new PollListAdapter(this, pollList);
        pollListView.setAdapter(pollListAdapter);
        pollFile = new File(this.getFilesDir(), "pollFile");
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
        }
        return(super.onOptionsItemSelected(item));
    }

    public void newPollButton(View view) {
        Intent newPollIntent = new Intent(this, EditorActivity.class);
        startActivityForResult(newPollIntent, NEW_POLL_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_POLL_REQUEST) {

            if(resultCode == RESULT_OK) {
                ArrayList<String> pollOptions = data.getStringArrayListExtra("pollOptions");
                Poll newPoll = pollBuilder.buildPoll(pollOptions);
                pollList.add(newPoll);
                pollListAdapter.updatePollArray(pollList);
            }
            else { /* do nothing */ }

        } else if (requestCode == UPDATED_POLL) {
            if (resultCode == RESULT_OK) {
                Bundle receivedBundle = data.getBundleExtra("newBundle");
                Poll updatedPoll = (Poll) receivedBundle.getSerializable("poll");
                int oldPoll = receivedBundle.getInt("pastPosition");
                pollList.add(updatedPoll);
                pollList.remove(oldPoll);
                pollListAdapter.updatePollArray(pollList);
            } else { /* do nothing */ }
        }
    }

    //Might not stay in this class
    private void saveState() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean  keep = true;
        try {
            fos = new FileOutputStream(pollFile);
            oos = new ObjectOutputStream(fos);
        } catch (Exception e) {
            keep = false;
            Log.e("VoteApp", "failed to suspend", e);
        }
        finally{
            try {
                if (oos != null)   oos.close();
                if (fos != null)   fos.close();
                if (keep == false) pollFile.delete();
            }
            catch (Exception e) { /* do nothing */ }
        }
        
    }
}

