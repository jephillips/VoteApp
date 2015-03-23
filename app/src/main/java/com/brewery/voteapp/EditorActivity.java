

package com.brewery.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/9/2015.
 */
public class EditorActivity extends ActionBarActivity {

    private LinearLayout pollEditorLayout;
    private int optionID = 1;
    private List<EditText> editTexts = new ArrayList<EditText>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_editor);
        pollEditorLayout = (LinearLayout) findViewById(R.id.pollEditorLayout);
        Button addButton = (Button) findViewById(R.id.addOptionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                addVoteOption();
            }
        });
    }

    private void addVoteOption() {
        LinearLayout editTextLayout = new LinearLayout(this);
        editTextLayout.setOrientation(LinearLayout.VERTICAL);
        pollEditorLayout.addView(editTextLayout);

        EditText editOption = new EditText(this);
        String optionTextID = "@+id/editOption" + optionID;
        editOption.setHint("Option" + optionID );
        editOption.setId(optionID++);
        editTextLayout.addView(editOption);
        editTexts.add(editOption);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
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
            case R.id.action_save_poll:
                saveAndReturn();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    //Constructs an array of Strings pulled from the Text Fields
    public void saveAndReturn() {
        ArrayList<String> pollOptions = new ArrayList<String>();
        String pollName = ((EditText)findViewById(R.id.pollNameField)).getText().toString();
        String pollQuestion = ((EditText)findViewById(R.id.pollQuestionField)).getText().toString();
        pollOptions.add(pollName);
        pollOptions.add(pollQuestion);
        for (EditText option : editTexts) {
            int currentOptionID = option.getId();
            if (option.equals(" ")){
                Toast errorToast = new Toast(this);
                errorToast.makeText(this, "You have not entered a value in field " + currentOptionID,
                        Toast.LENGTH_LONG);
                errorToast.show();
            }
            String currentOptionString = ((EditText)findViewById(currentOptionID)).getText()
                    .toString();
            pollOptions.add(currentOptionString);
        }
        Intent returnToMain = new Intent();
        returnToMain.putStringArrayListExtra("pollOptions", pollOptions);
        setResult(RESULT_OK, returnToMain);
        finish();
    }
}

