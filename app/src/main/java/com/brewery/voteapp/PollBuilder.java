package com.brewery.voteapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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
        //Sets the poll name/question and removes it from the optionsList
        newPoll.setPollName(optionsList.get(0));
        optionsList.remove(0);
        newPoll.setPollQuestion(optionsList.get(0));
        optionsList.remove(0);
        // Removes duplicates from array
        Set<String> removeDuplicateSet = new HashSet<String>();
        removeDuplicateSet.addAll(optionsList);
        optionsList.clear();
        optionsList.addAll(removeDuplicateSet);

        for (String optionString : optionsList) {
            //Creates a hashmap with a option string and vote value init to 0
            HashMap<String, AtomicInteger> newOption = new HashMap<String, AtomicInteger>();
            newOption.put(optionString, new AtomicInteger());
            newPoll.addChoice(newOption);
        }
        return newPoll;
    }

}

