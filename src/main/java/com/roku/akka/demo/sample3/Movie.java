package com.roku.akka.demo.sample3;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.io.Serializable;

public class Movie extends AbstractActor {
    String title;
    int likes = 0;

    static class LikeMsg implements Serializable {
        final String personName;

        LikeMsg(String personName) {
            this.personName = personName;
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Movie.LikeMsg.class, msg -> {
                    if (title == null) {
                        title = MovieDao.getTitle(self().path().name());

                    }
                    System.out.println(msg.personName + " liked movie " + title + ", total likes " + likes);
                })
                .build();
    }

}