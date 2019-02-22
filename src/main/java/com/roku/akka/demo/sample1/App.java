package com.roku.akka.demo.sample1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.Scanner;

public class App {


    static class Movie extends AbstractActor {
        private final String title;
        private int likes = 0;

        Movie(String title) {
            this.title = title;
        }

        static class LikeMsg{
            private final String name;

            LikeMsg(String name) {
                this.name = name;
            }
        }

        @Override
        public Receive createReceive() {
            return ReceiveBuilder.create()
                    .match(LikeMsg.class, msg -> {
                        likes++;
                        System.out.println(msg.name + " liked " + title + "total=" + likes);
                    })
                    .build();
        }
    }


    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("system");
        ActorRef movieRef = system.actorOf(Props.create(Movie.class, "Die Hard"), "1");
        movieRef.tell(new Movie.LikeMsg("Polina"), ActorRef.noSender());

        System.out.println("Enter to terminate");
        Scanner scanner = new Scanner(System. in);
        scanner. nextLine();

    }
}
