package com.brewery.voteapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by jephillips on 3/17/15.
 */
public class VoteApplication extends Application {

    private static VoteApplication appSingleton;

    public VoteApplication getInstance() {
        return appSingleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appSingleton = this;
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "APPLICATION ID", "CLIENT KEY");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
