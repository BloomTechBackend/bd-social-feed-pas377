package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.helpers.LocalDateTimeAdapter;
import com.bloomtech.socialfeed.models.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepository {
    private static final String POST_DATA_PATH = "src/resources/PostData.json";

    public PostRepository() {
    }

    public List<Post> getAllPosts() {
        //TODO: return all posts from the PostData.json file
        return new ArrayList<>();
    }

    public List<Post> findByUsername(String username) {
        return getAllPosts()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Post> addPost(Post post) {
        List<Post> allPosts = new ArrayList<>();
        allPosts.add(post);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(POST_DATA_PATH));
            bufferedWriter.write(gson.toJson(allPosts));

            bufferedWriter.close();

        } catch (IOException e) {
            e.getStackTrace();
        }

        return allPosts;
    }
}
