package com.roku.akka.demo.sample3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import java.util.Scanner;

/**
 * java   -DAKKA_PORT=2552 -cp /users/pvlasenko/projects/sr/akka-demo/build/libs/*:.  com.roku.akka.demo.sample3.App
 */
public class App {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ClusterShardingSettings settings = ClusterShardingSettings.create(system);
        ClusterSharding.get(system).start("Movie", Props.create(Movie.class), settings, new MovieMsgExtractor());


        Scanner scanner = new Scanner(System.in);
        while (scanner.nextLine()!=null){
            ClusterSharding.get(system).shardRegion("Movie")
                    .tell(new MovieShardMessage("1", new Movie.LikeMsg("Polina")), ActorRef.noSender());
            ClusterSharding.get(system).shardRegion("Movie")
                    .tell(new MovieShardMessage("2", new Movie.LikeMsg("Polina")), ActorRef.noSender());
        }



    }
}
