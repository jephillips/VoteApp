package com.brewery.voteapp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jephillips on 3/10/15.
 */
public class PollBuilder {

    private static PollBuilder instance = null;

    protected PollBuilder() {
        // Exists only to defeat instantiation.
    }

    public static PollBuilder getInstance() {
        if(instance == null) {
            instance = new PollBuilder();
        }
        return instance;
    }

    public Poll buildPoll(ArrayList<String> optionsList) {

        Poll newPoll = new Poll();
        //Sets the poll name and removes it from the optionsList
        newPoll.setPollName(optionsList.get(0));
        optionsList.remove(0);

        for (String optionString : optionsList) {
            //Creates an option hashmap with a option string and vote value init to 0
            HashMap<String, Integer> newOption = new HashMap<String, Integer>();
            newOption.put(optionString, 0);
            newPoll.addChoice(newOption);
        }

        return newPoll;
    }
}

