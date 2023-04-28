package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SourceFeed implements Source {
    private final PostRepository postRepository = new PostRepository();

    private List<Post> posts;
    private List<Observer> observers;

    public SourceFeed() {
        this.posts = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }
    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }
    @Override
    public void updateAll() {
        for (Observer o : observers)
            o.update();
    }

    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }

    public Post addPost(User user, String body) {
        Post post = new Post(user.getUsername(),
                LocalDateTime.now(),
                body);
        posts = postRepository.addPost(post);
        updateAll();

        return post;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
