package com.roku.akka.demo.sample3;

import java.io.Serializable;

public class MovieShardMessage implements Serializable {
    final String movieId;
    final Object msg;

    public MovieShardMessage(String movieId, Object msg) {
        this.movieId = movieId;
        this.msg = msg;
    }
}
