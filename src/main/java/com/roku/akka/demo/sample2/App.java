package com.roku.akka.demo.sample2;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.Scanner;

public class App {

    static class Movie extends AbstractActor {
        String title;
        int likes = 0;
        Receive moderatedBehavior;
        Receive moderationBehavior;

        Movie(String title) {
            this.title = title;
            this.moderatedBehavior = ReceiveBuilder.create()
                    .match(LikeMsg.class, msg -> {
                        likes++;
                        System.out.println(msg.personName + " liked movie " + title + ", total likes " + likes);

                    })
                    .build();

            this.moderationBehavior = ReceiveBuilder.create()
                    .match(LikeMsg.class, msg -> {
                        System.out.println("Dear " + msg.personName + ",movie is freezed now, please wait");

                    })
                    .match(ApproveMsg.class, msg -> {
                        System.out.println(msg.personName + " approved movie " + title);
                        getContext().become(moderatedBehavior);
                    })
                    .build();

        }

        static class LikeMsg {
            final String personName;

            LikeMsg(String personName) {
                this.personName = personName;
            }
        }

        static class ApproveMsg {
            final String personName;

            ApproveMsg(String personName) {
                this.personName = personName;
            }
        }


        @Override
        public Receive createReceive() {
            return moderationBehavior;
        }

    }


    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef ref = system.actorOf(Props.create(Movie.class, "Die Hard"));
        ref.tell(new Movie.LikeMsg("Polina"), ActorRef.noSender());
        ref.tell(new Movie.ApproveMsg("Amit"), ActorRef.noSender());
        ref.tell(new Movie.LikeMsg("Jay"), ActorRef.noSender());
        ref.tell(new Movie.ApproveMsg("Amit"), ActorRef.noSender());


        System.out.println("Enter to terminate");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

    }
}
