package com.brewery.voteapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jephillips on 3/12/15.
 */
class PollListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private final Context context;
    private ArrayList<Poll> adapterPollList;
    private int NEW_VOTE = 2;
    private PollBuilder pollBuilder = new PollBuilder();

    PollListAdapter(Context context, ArrayList<Poll> pollList) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        adapterPollList = pollList;
    }

    @Override
    public int getCount() {
        return adapterPollList.size();
    }

    public void updatePollArray(ArrayList<Poll> pollList) {
        ThreadPreconditions.checkOnMainThread();
        this.adapterPollList = pollList;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return adapterPollList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null) {
                convertView = mInflater.inflate(R.layout.poll_list_view_layout, parent, false);
                holder = new ViewHolder();
                holder.pollNameView = (TextView) convertView.findViewById(R.id.poll_name_text_view);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

        ImageButton deleteButton = (ImageButton)convertView.findViewById(R.id.deletePollButton);
        ImageButton editButton = (ImageButton)convertView.findViewById(R.id.editPollButton);
        ImageButton voteButton = (ImageButton)convertView.findViewById(R.id.votePollButton);
        final Poll currentPoll = adapterPollList.get(position);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterPollList.remove(position);
                updatePollArray(adapterPollList);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delivers current poll to the results view for display
                Bundle pollBundle = new Bundle();
                pollBundle.putSerializable("poll", currentPoll);
                Intent resultsIntent = new Intent(view.getContext(), ResultsActivity.class);
                resultsIntent.putExtras(pollBundle);
                context.startActivity(resultsIntent);
            }
        });
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delivers current poll to vote view for mutation
                Bundle pollBundle = new Bundle();
                pollBundle.putSerializable("poll", currentPoll);
                pollBundle.putInt("position", position);
                Intent voteIntent = new Intent(view.getContext(), VoteActivity.class);
                voteIntent.putExtras(pollBundle);
                ((Activity)context).startActivityForResult(voteIntent, NEW_VOTE);
            }
        });
        holder.pollNameView.setText(currentPoll.getPollName());
        return convertView;
    }

    private class ViewHolder {
        TextView pollNameView;
    }



}
