package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.helpers.LocalDateTimeAdapter;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.observerpattern.OUserFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepository {
    private static final String POST_DATA_PATH = "src/resources/PostData.json";
    private OUserFeed oUserFeed;

    public PostRepository() {
    }

    public List<Post> getAllPosts() {
        //TODO: read all posts from the PostData.json file
        List<Post> allPosts = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .create();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(POST_DATA_PATH));
            allPosts = gson.fromJson(bufferedReader, new TypeToken<List<Post>>(){}.getType());

            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }

        if (allPosts == null) {
            return new ArrayList<>();
        }

        return allPosts;
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