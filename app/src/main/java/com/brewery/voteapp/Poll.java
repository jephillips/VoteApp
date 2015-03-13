package com.brewery.voteapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Josh on 3/9/2015.
 */
public class Poll implements Serializable {

    public String pollName;
    public String pollQuestion;
    public ArrayList<HashMap<String, AtomicInteger>> choiceList = new ArrayList<HashMap<String, AtomicInteger>>();
    public int totalVotes;


    public void setPollQuestion(String question) {
        pollQuestion = question;
    }




    public void setPollName(String name) {
        pollName = name;
    }

    public void incrementTotalVotes() {
        totalVotes++;
    }

    public void addChoice(HashMap<String, AtomicInteger> newChoice) {
        choiceList.add(newChoice);
    }

    public void removeChoice(int position) {
        choiceList.remove(position);
    }


    public int getTotalVotes() {
        return totalVotes;
    }

    public HashMap<String, AtomicInteger> getChoice(int position) {
        return choiceList.get(position);
    }

    public String getPollName(){
        return pollName;
    }

    public String getPollQuestion() { return pollQuestion;}
}