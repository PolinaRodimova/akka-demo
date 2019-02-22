package com.roku.akka.demo.sampleOk;

import java.util.List;

public class CommentsResponseMsg {
    final List<Comment> comments;

    public CommentsResponseMsg(List<Comment> comments) {
        this.comments = comments;
    }
}
