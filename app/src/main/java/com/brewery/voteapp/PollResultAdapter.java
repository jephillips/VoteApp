package com.brewery.voteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
        return poll.getChoiceList().size();
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
        ViewHolder holder;

        if(convertView==null) {
            convertView = mInflater.inflate(R.layout.poll_results_list_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.optionText.setText(String.valueOf(poll.getChoice(position).getChoiceString()));
        holder.voteCountText.setText(String.valueOf(poll.getChoice(position).getVoteCount()));

        return convertView;
    }

    public class ViewHolder {
        @InjectView(R.id.result_option_view)TextView optionText;
        @InjectView(R.id.result_vote_count)TextView voteCountText;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}