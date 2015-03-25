package com.brewery.voteapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jephillips on 3/23/15.
 */
public class Choice implements Parcelable, Serializable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(choiceString);
        dest.writeInt(voteCount);
    }

    public Choice(Parcel in) {
        super();
        readFromParcel(in);
    }


    public static final Parcelable.Creator<Choice> CREATOR = new Parcelable.Creator<Choice>() {
        public Choice createFromParcel(Parcel in) {
            return new Choice(in);
        }

        public Choice[] newArray(int size) {

            return new Choice[size];
        }

    };

    public void readFromParcel(Parcel in) {
        choiceString= in.readString();
        voteCount = in.readInt();

    }

}
