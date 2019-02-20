package com.roku.akka.demo.sample3;

import akka.cluster.sharding.ShardRegion;

public class MovieMsgExtractor implements ShardRegion.MessageExtractor {

    private static final int numberOfShards = 2;

    @Override
    public String entityId(Object message) {
        if (message instanceof MovieShardMessage) {
            return ((MovieShardMessage) message).movieId;
        } else {
            throw new IllegalArgumentException("you should send CanonicalMovieShardMessage to sharding actor, current msg=" + message);
        }
    }

    @Override
    public Object entityMessage(Object message) {
        if (message instanceof MovieShardMessage) {
            return ((MovieShardMessage) message).msg;
        } else {
            throw new IllegalArgumentException("you should send CanonicalMovieShardMessage to sharding actor, current msg=" + message);
        }
    }

    @Override
    public String shardId(Object message) {
        if (message instanceof MovieShardMessage) {
            String id = ((MovieShardMessage) message).movieId;
            int shardId = Math.abs(Integer.valueOf(id) % numberOfShards);
            return String.valueOf(shardId);
        } else {
            throw new IllegalArgumentException("you should send CanonicalMovieShardMessage to sharding actor, current msg=" + message);
        }
    }
}
