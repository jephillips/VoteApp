package com.brewery.voteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jephillips on 3/12/15.
 */
class PollListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private final Context context;
    private ArrayList<Poll> adapterPollList;

    PollListAdapter(Context context, ArrayList<Poll> pollList) {
//            super(ManagerActivity.this, R.layout.poll_list_view_layout, R.id.pollListView,
//                    pollList);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            if(convertView == null)
                convertView = mInflater.inflate(R.layout.poll_list_view_layout, null);

        TextView pollNameTextView = (TextView)convertView.findViewById(R.id.poll_name_text_view);
        Poll currentPoll = adapterPollList.get(position);

        pollNameTextView.setText(currentPoll.getPollName());



        return convertView;
    }
}
