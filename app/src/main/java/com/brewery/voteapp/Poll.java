package com.brewery.voteapp;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;


/**
 * Created by Josh on 3/9/2015.
 */
public class Poll implements Parcelable {

    public String pollName;
    public String pollQuestion;
    public ArrayList<Choice> choiceList = new ArrayList<Choice>();
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

    public void addChoice(Choice newChoice) {
        choiceList.add(newChoice);
    }

    public void removeChoice(int position) {
        choiceList.remove(position);
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public Choice getChoice(int position) {
        return choiceList.get(position);
    }

    public String getPollName(){
        return pollName;
    }

    public String getPollQuestion() { return pollQuestion;}

    public ArrayList<Choice> getChoiceList(){
        return choiceList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pollName);
        dest.writeString(pollQuestion);
        dest.writeInt(totalVotes);

    }

    public Poll(Parcel in) {
        super();
        readFromParcel(in);
    }

    public Poll() {

    }

    public static final Parcelable.Creator<Poll> CREATOR = new Parcelable.Creator<Poll>() {
        public Poll createFromParcel(Parcel in) {
            return new Poll(in);
        }

        public Poll[] newArray(int size) {

            return new Poll[size];
        }

    };

    public void readFromParcel(Parcel in) {
        pollName = in.readString();
        pollQuestion = in.readString();
        totalVotes = in.readInt();

    }

}