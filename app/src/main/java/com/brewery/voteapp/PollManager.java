package com.brewery.voteapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by jephillips on 3/24/15.
 */
public class PollManager {

    static Context context;
    static SharedPreferences sharedPreferences;
    private static ArrayList<Poll> mPollArray;

    public PollManager(Context context) {
        PollManager.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void savePollArray(ArrayList<Poll> pollArrayList) {

            Gson gson = new Gson();
            SharedPreferences.Editor mEditor = sharedPreferences.edit();
            String serializedArray = gson.toJson(pollArrayList);
            System.out.println(serializedArray);
            mEditor.putString("pollArrayList", serializedArray);
            mEditor.apply();
    }

    public ArrayList<Poll> loadPolls() {

        if(mPollArray == null) {
            mPollArray = new Gson().fromJson(sharedPreferences.getString("pollArrayList", null),
                    new TypeToken<ArrayList<Poll>>(){}.getType());

            if (mPollArray == null) {
                mPollArray = new ArrayList();
            }
        }

        return mPollArray;
    }
    public String serializePoll(Poll poll) {
        Gson gson = new Gson();
        String serializedPoll = gson.toJson(poll);
        return serializedPoll;
    }

    public Poll deserializePoll(String poll) {
        Poll deserializedPoll = new Gson().fromJson(poll, new TypeToken<Poll>(){}.getType());
        return deserializedPoll;

    }


}
