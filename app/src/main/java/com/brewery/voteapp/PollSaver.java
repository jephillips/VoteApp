package com.brewery.voteapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by jephillips on 3/24/15.
 */
public class PollSaver {

    static Context context;

    public PollSaver(Context context) {
        this.context = context;
    }

//    static void serialize(ArrayList<Poll> pollArrayList) {
//
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//
//        if (awesomeObjectCacheFile != null) {
//            Gson gson = new Gson();
//            String literal = gson.toJson(model);
//            FileWriter writer = null;
//            try {
//                writer = new FileWriter(awesomeObjectCacheFile, false); // false overwrites ie do not append
//                writer.write(literal);
//            } catch (Exception oops) {
//                oops.printStackTrace();
//            }
//            if (writer != null) try { writer.close(); } catch (Exception ignore) { }
//        }
//    }
//
//    static ArrayList<Poll> deserialize(long id) {
//        StringBuilder sb = new StringBuilder();
//        Gson gson = new Gson();
//        ArrayList<Poll> pollArrayList = null;
//        Poll poll = null;
//        try {
//            SharedPreferences
//            sb.setLength(0);
//            BufferedReader br = new BufferedReader(new FileReader(awesomeObjectCacheFile));
//            String line;
//            while ((line = br.readLine()) != null) sb.append(line);
//            poll = gson.fromJson(sb.toString(), Poll.class);
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pollArrayList;
//    }

}
