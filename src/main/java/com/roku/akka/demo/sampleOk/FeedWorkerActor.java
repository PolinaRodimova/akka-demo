package com.roku.akka.demo.sampleOk;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.PoisonPill;
import akka.japi.pf.ReceiveBuilder;
import scala.concurrent.duration.Duration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeedWorkerActor extends AbstractActor {
    private final int feedId;
    private List<Comment> comments = null;
    private List<Like> likes = null;
    private Cancellable tickTask;
    private ActorRef sender;

    FeedWorkerActor(int feedId) {
        this.feedId = feedId;
    }

    @Override
    public void aroundPreStart() {
        super.aroundPreStart();
        tickTask = getContext().getSystem().scheduler().scheduleOnce(
                Duration.create(1, TimeUnit.MINUTES),
                getSelf(), PoisonPill.getInstance(), getContext().dispatcher(), ActorRef.noSender());
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(FeedRequestMsg.class, msg->{
                    sender = sender();
                    context().actorSelection("/user/likesDao").tell(new AskLikesMsg(feedId), self());
                    context().actorSelection("/user/commentsDao").tell(new AskCommentsMsg(feedId), self());
                })
                .match(LikesResponseMsg.class, msg -> {
                    likes = msg.likes;
                    completeIfNeeded();
                })
                .match(CommentsResponseMsg.class, msg -> {
                    comments = msg.comments;
                    completeIfNeeded();
                })
                .build();
    }

    private void completeIfNeeded() {
        if (likes != null && comments != null) {
            complete();
        }
    }

    private void complete() {
        sender.tell(new FeedResponseMsg(likes, comments), self());
        //we are done, kill it now!
        tickTask.cancel();
        self().tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}