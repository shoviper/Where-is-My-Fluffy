package com.sda_project.myfluffy.common.observers.listeners.post;

import com.sda_project.myfluffy.common.observers.events.post.PostCreatedEvent;
import com.sda_project.myfluffy.notification.Notification;
import com.sda_project.myfluffy.post.model.Post;
import com.sda_project.myfluffy.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostCreatedListener {

    private Notification notification;

    @EventListener
    public void handlePostCreated(PostCreatedEvent event) {
        Post post = event.getPost();
        User author = post.getPostOwner();
        System.out.println("New post created by " + author.getName() + ": " + post.getTitle());
        notifyAuthor(author, post);
    }

    private void notifyAuthor(User author, Post post) {
        String message = "Dear " + author.getName() + ", your post '" + post.getTitle() + "' has been published.";
        System.out.println("Sending notification: " + message);
        notification.send(message);
    }
}