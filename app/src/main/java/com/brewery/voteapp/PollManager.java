package com.brewery.voteapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    static void savePolls(ArrayList<Poll> pollArrayList) {

            Gson gson = new Gson();
            SharedPreferences.Editor mEditor = sharedPreferences.edit();
            String serializedArray = gson.toJson(pollArrayList);
            mEditor.putString("pollArrayList", serializedArray);
            mEditor.apply();

    }

    static ArrayList<Poll> loadPolls() {

        if(mPollArray == null) {
            mPollArray = new Gson().fromJson(sharedPreferences.getString("pollArrayList", null),
                    new TypeToken<ArrayList<Poll>>(){}.getType());

            if (mPollArray == null) {
                mPollArray = new ArrayList();
            }
        }

        return mPollArray;
    }

}
