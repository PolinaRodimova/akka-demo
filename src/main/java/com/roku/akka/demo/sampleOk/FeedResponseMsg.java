package com.roku.akka.demo.sampleOk;

import java.util.List;

public class FeedResponseMsg {
    final List<Like> likes;
    final List<Comment> comments;

    public FeedResponseMsg(List<Like> likes, List<Comment> comments) {
        this.likes = likes;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "FeedResponseMsg{" +
                "likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
