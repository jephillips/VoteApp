package com.brewery.voteapp;

import android.os.Looper;

/**
 * Created by jephillips on 3/12/15.
 */
public class ThreadPreconditions {

    public static void checkOnMainThread() {
        if (BuildConfig.DEBUG) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                throw new IllegalStateException("This method should be called from the Main Thread");
            }
        }
    }
}

