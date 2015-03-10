package com.brewery.voteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Josh on 3/9/2015.
 */
public class EditorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_editor);
    }

    //This will eventually return a Poll
    public void saveAndReturn(View view) {
        Intent mainReturn = new Intent(this, ManagerActivity.class);
        startActivity(mainReturn);
    }
}
