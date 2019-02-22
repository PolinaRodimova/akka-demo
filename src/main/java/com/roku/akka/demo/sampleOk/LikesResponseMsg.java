package com.roku.akka.demo.sampleOk;

import java.util.List;

public class LikesResponseMsg {
    final List<Like> likes;

    public LikesResponseMsg(List<Like> likes) {
        this.likes = likes;
    }
}
