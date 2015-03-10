

package com.brewery.voteapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/9/2015.
 */
public class EditorActivity extends Activity {


    private LinearLayout main;
    private int optionID = 1;
    private List<EditText> editTexts = new ArrayList<EditText>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_editor);

        main = (LinearLayout) findViewById(R.id.pollEditorLayout);

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
        main.addView(editTextLayout);

        EditText editOption = new EditText(this);
        String optionTextID = "@+id/editOption" + optionID;
        editOption.setHint("Option" + optionID );
        editOption.setId(optionID++);
        editTextLayout.addView(editOption);
        editTexts.add(editOption);

    }


    //This will eventually return a Poll
    public void saveAndReturn(View view) {
        ArrayList<String> pollOptions = new ArrayList<String>();
        String pollName = ((EditText)findViewById(R.id.pollNameField)).getText().toString();
        pollOptions.add(pollName);

        for (EditText option : editTexts) {
            int currentOptionID = option.getId();
            String currentOptionString = ((EditText)findViewById(currentOptionID)).getText()
                    .toString();
            pollOptions.add(currentOptionString);
        }

    }
}

