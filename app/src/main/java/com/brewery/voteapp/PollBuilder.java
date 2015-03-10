package com.brewery.voteapp;

import java.util.ArrayList;

/**
 * Created by jephillips on 3/10/15.
 */
public class PollBuilder {

    PollBuilder() {

    }

    public Poll buildPoll(ArrayList<String> optionsList) {
        return new Poll();
    }
}
