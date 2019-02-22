package com.roku.akka.demo.sampleOk;

public class AskLikesMsg {
    private final int feedId;

    AskLikesMsg(int feedId) {
        this.feedId = feedId;
    }
}