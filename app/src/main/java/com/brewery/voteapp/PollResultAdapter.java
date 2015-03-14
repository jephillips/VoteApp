package com.brewery.voteapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by jephillips on 3/13/15.
 */
public class PollResultAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    Context context;
    Poll poll;

    PollResultAdapter(Context context, Poll poll) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.poll = poll;
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
            convertView = mInflater.inflate(R.layout.poll_results_list_view, parent, false);
        }

        TextView optionText = (TextView) convertView.findViewById(R.id.result_option_view);
        TextView voteCountText = (TextView) convertView.findViewById(R.id.result_vote_count);
        //Removes sloppy formatting from hashmap returns
        optionText.setText(poll.getChoice(position).keySet().toString().replaceAll("[\\[\\],]",""));
        voteCountText.setText(poll.getChoice(position).values().toString().
                replaceAll("[\\[\\],]",""));
        return convertView;
    }
}