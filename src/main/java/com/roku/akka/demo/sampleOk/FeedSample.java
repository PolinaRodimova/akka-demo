package com.roku.akka.demo.sampleOk;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.Scanner;

public class FeedSample {

    static class FakeActor extends AbstractActor{

        @Override
        public Receive createReceive() {
            return ReceiveBuilder.create()
                    .match(FeedResponseMsg.class, msg->{
                        System.out.println("done: " + msg);
                    })
                    .build();
        }
    }



        public static void main(String[] args) {
            ActorSystem system = ActorSystem.create("system");
            system.actorOf(Props.create(LikesDaoActor.class), "likesDao"); //it is just ot receive and log message from FeedWorkerActor
            system.actorOf(Props.create(CommentsDaoActor.class), "commentsDao"); //it is just ot receive and log message from FeedWorkerActor

            ActorRef fakeRef = system.actorOf(Props.create(FakeActor.class)); //it is just ot receive and log message from FeedWorkerActor


            ActorRef feedWorker = system.actorOf(Props.create(FeedWorkerActor.class, 123), "123");
            feedWorker.tell(new FeedRequestMsg(), fakeRef);

            System.out.println("Enter to terminate");
            Scanner scanner = new Scanner(System. in);
            scanner. nextLine();

        }

}
