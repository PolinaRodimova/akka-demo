package com.roku.akka.demo.sampleOk;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.Collections;

public class CommentsDaoActor extends AbstractActor{
    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(AskCommentsMsg.class, msg->{
                    sender().tell(new CommentsResponseMsg(Collections.singletonList(new Comment())), self());
                })
                .build();
    }
}
