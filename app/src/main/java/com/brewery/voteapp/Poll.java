package com.brewery.voteapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Josh on 3/9/2015.
 */
public class Poll implements Serializable {

    public String pollName;
    public String pollQuestion;
    public ArrayList<HashMap> choiceList = new ArrayList<HashMap>();
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

    public void addChoice(HashMap<String, Integer> newChoice) {
        choiceList.add(newChoice);
    }

    public void removeChoice(int position) {
        choiceList.remove(position);
    }


    public int getTotalVotes() {
        return totalVotes;
    }

    public HashMap<String, Integer> getChoice(int position) {
        return choiceList.get(position);
    }

    public String getPollName(){
        return pollName;
    }

    public String getPollQuestion() { return pollQuestion;}
}