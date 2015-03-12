package com.brewery.voteapp;

import android.content.Context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            if(convertView == null)
                convertView = mInflater.inflate(R.layout.poll_list_view_layout, parent, false);

        //This block allows buttons to access the poll in their row
        ImageButton deleteButton = (ImageButton)convertView.findViewById(R.id.deletePollButton);
        ImageButton editButton = (ImageButton)convertView.findViewById(R.id.editPollButton);
        ImageButton voteButton = (ImageButton)convertView.findViewById(R.id.votePollButton);

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
                int rowPosition = (Integer) view.getTag();

            }
        });
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rowPosition = (Integer) view.getTag();
            }
        });

        TextView pollNameTextView = (TextView)convertView.findViewById(R.id.poll_name_text_view);
        Poll currentPoll = adapterPollList.get(position);


        pollNameTextView.setText(currentPoll.getPollName());



        return convertView;
    }
}
