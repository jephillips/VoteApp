package com.brewery.voteapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jephillips on 3/13/15.
 */
public class PollVoteAdapter extends BaseAdapter{

    LayoutInflater mInflater;
    Context context;
    Poll poll;
    int pastPosition;
    Bundle pollBundle = new Bundle();

    PollVoteAdapter(Context context, Poll poll, int pastPosition) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.poll = poll;
        this.pastPosition = pastPosition;

    }
    @Override
    public int getCount() {
        return poll.choiceList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = mInflater.inflate(R.layout.vote_choices_list_view, parent, false);
        }
        final String option = poll.getChoice(position).keySet().toString()
                .replaceAll("[\\[\\],]", "");

        TextView optionTextView = (TextView)convertView.findViewById(R.id.vote_option_text);
        optionTextView.setText(option);
        Button voteButton = (Button)convertView.findViewById(R.id.vote_list_button);

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              for (int i = 0; i<poll.choiceList.size(); i++) {
                  if (poll.getChoice(i).containsKey(option)) {
                      HashMap<String, AtomicInteger> choiceKey = poll.getChoice(i);
                      AtomicInteger voteValue = choiceKey.get(option);
                      voteValue.incrementAndGet();
                      poll.incrementTotalVotes();
                  }
              }
                  pollBundle.putParcelable("poll", poll);
                  pollBundle.putInt("pastPosition", pastPosition);
                  Intent returnToMain = new Intent();
                  returnToMain.putExtra("newBundle", pollBundle);
                  CharSequence text = "Thank you for voting.";
                  Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                  toast.show();
                  ((Activity) context).setResult(Activity.RESULT_OK, returnToMain);
                  ((Activity) context).finish();
            }
        });
        return convertView;
    }


}
