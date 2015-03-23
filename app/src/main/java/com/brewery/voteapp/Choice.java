package com.brewery.voteapp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jephillips on 3/23/15.
 */
public class Choice {
    private Integer voteCount;
    private String choiceString;

    public Choice(String choiceString){
        this.choiceString = choiceString;
        voteCount = 0;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public String getChoiceString() {
        return choiceString;
    }

    public void incrementVoteCount() {
        voteCount++;
    }


}
