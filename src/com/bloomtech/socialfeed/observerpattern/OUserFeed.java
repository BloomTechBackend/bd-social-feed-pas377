package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;

import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class OUserFeed implements Observer {
    private User user;
    private List<Post> feed;
    private List<Observer> observers;
  
    private SourceFeed sourceFeed;

    public OUserFeed(User user) {
        this.user = user;
        this.feed = new ArrayList<>();
        this.observers = new ArrayList<>();

        this.sourceFeed = new SourceFeed();
        App.sourceFeed.attach(this);
        update();
    }

    @Override
    public void update() {
        List<Post> posts = sourceFeed.getAllPosts();
        if (user != null) {
            for (String username : user.getFollowing()) {
                for (Post post : posts) {
                    if (post.getUsername().equals(username)) {
                        feed.add(post);
                    }
                }
            }
        }
    }

    public User getUser() {
        return user;
    }

    public List<Post> getFeed() {
        return feed;
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void updateAll() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
