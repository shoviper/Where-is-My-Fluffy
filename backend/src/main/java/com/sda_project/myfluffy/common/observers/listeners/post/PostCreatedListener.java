package com.sda_project.myfluffy.common.observers.listeners.post;

import com.sda_project.myfluffy.common.observers.events.post.PostCreatedEvent;
import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import com.sda_project.myfluffy.notification.dto.NotificationCreateDto;
import com.sda_project.myfluffy.notification.factory.NotificationFactory;
import com.sda_project.myfluffy.notification.factory.post.PostNotificationFactory;
import com.sda_project.myfluffy.notification.service.INotificationService;
import com.sda_project.myfluffy.post.model.Post;
import com.sda_project.myfluffy.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostCreatedListener {

    private INotificationService iNotificationService;

    @EventListener
    public void handlePostCreated(PostCreatedEvent event) {
        Post post = event.getPost();
        User author = post.getPostOwner();

        sendNotification(author, post);
    }

    private void sendNotification(User author, Post post) {
        String message = "Dear " + author.getName() + ", your post '" + post.getTitle() + "' has been published.";
        System.out.println("Sending notification: " + message);

        NotificationCreateDto notificationCreateDto = new NotificationCreateDto();
        notificationCreateDto.setMessage(message);
        notificationCreateDto.setNotificationType(NotificationType.NOTIFICATION_CREATED);
        iNotificationService.createNotification(author, notificationCreateDto);

        NotificationFactory PostNotificationFactory = new PostNotificationFactory(message);
        PostNotificationFactory.sendNotification(NotificationType.NOTIFICATION_CREATED);
    }
}