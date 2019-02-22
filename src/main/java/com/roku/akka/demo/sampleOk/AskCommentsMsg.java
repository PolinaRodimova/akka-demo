package com.roku.akka.demo.sampleOk;

public class AskCommentsMsg {
    private final int feedId;

    AskCommentsMsg(int feedId) {
        this.feedId = feedId;
    }
}