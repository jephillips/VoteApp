package com.brewery.voteapp;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Josh on 3/9/2015.
 */
public class Poll {

    private LinkedList<HashMap> choiceList = new LinkedList<HashMap>();

    public void addChoice(HashMap<String,Integer> newChoice) {
        choiceList.add(newChoice);
    }

    public HashMap<String,Integer> getChoice(int position) {
        // not sure about this implementation
        return choiceList.get(position);
    }

    public void removeChoice(int position) {
        choiceList.remove(position);
    }
}
