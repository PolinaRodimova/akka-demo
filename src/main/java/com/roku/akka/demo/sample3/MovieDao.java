package com.roku.akka.demo.sample3;

import java.util.HashMap;
import java.util.Map;

public class MovieDao {

    private static final Map<String, String> movies = new HashMap<>();

    static {
        movies.put("1", "Die Hard");
        movies.put("2", "Die Hard 2 ");
    }

    static String getTitle(String id) {
        String title = movies.get(id);
        if (title == null) {
            throw new IllegalArgumentException("unknown id=" + id);
        }
        return title;
    }
}
