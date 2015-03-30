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

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jephillips on 3/12/15.
 */
class PollListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private final Context context;
    private ArrayList<Poll> adapterPollList;
    private int NEW_VOTE = 2;

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
        Poll currentPoll = adapterPollList.get(position);
        ViewHolder holder;

        if(convertView==null) {
                convertView = mInflater.inflate(R.layout.poll_list_view_layout, parent, false);
                holder = new ViewHolder(convertView,position);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

        holder.pollNameView.setText(currentPoll.getPollName());
        return convertView;
    }

    class ViewHolder {
        int position;
        Poll currentPoll;
        View view;

        public ViewHolder(View view, int position) {
            ButterKnife.inject(this, view);
            this.position = position;
            currentPoll = adapterPollList.get(position);
            this.view = view;
        }
        @InjectView(R.id.poll_name_text_view) TextView pollNameView;
        @InjectView(R.id.deletePollButton)ImageButton deleteButton;
        @InjectView(R.id.editPollButton)ImageButton editButton;
        @InjectView(R.id.votePollButton)ImageButton voteButton;

        @OnClick(R.id.deletePollButton)
        public void deletePoll() {
           adapterPollList.remove(position);
           updatePollArray(adapterPollList);
       }
        @OnClick(R.id.editPollButton)
        public void viewResults() {
                //Delivers current poll to the results view for display
                Bundle pollBundle = new Bundle();
                pollBundle.putParcelable("poll", currentPoll);
                pollBundle.putParcelableArrayList("choiceList", currentPoll.getChoiceList());
                Intent resultsIntent = new Intent(view.getContext(), ResultsActivity.class);
                resultsIntent.putExtras(pollBundle);
                context.startActivity(resultsIntent);
            }
        @OnClick(R.id.votePollButton)
        public void voteOnPoll(View view) {
            //Delivers current poll to vote view for mutation
            Bundle pollBundle = new Bundle();
            pollBundle.putParcelable("poll", currentPoll);
            pollBundle.putInt("position", position);
            pollBundle.putParcelableArrayList("choiceList", currentPoll.getChoiceList());
            Intent voteIntent = new Intent(view.getContext(), VoteActivity.class);
            voteIntent.putExtras(pollBundle);
            ((Activity)context).startActivityForResult(voteIntent, NEW_VOTE);
        }
    }



}
