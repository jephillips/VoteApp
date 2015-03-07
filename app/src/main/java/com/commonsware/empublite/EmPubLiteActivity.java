package com.commonsware.empublite;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EmPubLiteActivity extends Activity {


    private int option1, option2,option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }


    public void vote(View view) {

        RadioGroup optionGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        int option = optionGroup.getCheckedRadioButtonId();
        if (option == 2131165186) option1++;
        if (option == 2131165187) option2++;
        if (option == 2131165188) option3++;

    }

    public void getResults(View view) {
        CharSequence text = "Option 1: " + option1 + " - Option 2 : " + option2 +
                " Option 3: " + option3;
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
