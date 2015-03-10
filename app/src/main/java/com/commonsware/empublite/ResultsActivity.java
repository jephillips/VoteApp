package com.commonsware.empublite;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


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
        setContentView(R.layout.vote_screen);
        returnToManagerButton = (Button) findViewById(R.id.returnToManagerButton);
        listView = (ListView) findViewById(R.id.listView);

        // Populate the results ArrayList with data from the Poll object being passed along with the
        // intent
    }

    public void returnToManager(View view) {

        Intent returnIntent = new Intent(this, ManagerActivity.class);
    }
}
