package com.roku.akka.demo.sampleOk;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.Collections;

public class LikesDaoActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(AskLikesMsg.class, msg->{
                    sender().tell(new LikesResponseMsg(Collections.singletonList(new Like())), self());
                })
                .build();
    }
}
