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

    public OUserFeed(User user) {
        this.user = user;
        this.feed = new ArrayList<>();
        //TODO: update OUserFeed in constructor after implementing observer pattern
    }

    public User getUser() {
        return user;
    }

    public List<Post> getFeed() {
        return feed;
    }

    @Override
    public void update() {
//        List<Post> posts = sourceFeed.getAllPosts();
//        if (user != null) {
//            for (String username : user.getFollowing()) {
//                for (Post post : posts) {
//                    if (post.getUsername().equals(username)) {
//                        feed.add(post);
//                    }
//                }
//            }
//        }
    }
}
