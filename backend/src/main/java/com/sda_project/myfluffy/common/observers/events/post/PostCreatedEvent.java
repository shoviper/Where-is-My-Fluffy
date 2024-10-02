package com.sda_project.myfluffy.common.observers.events.post;

import com.sda_project.myfluffy.common.observers.events.BaseEvent;
import com.sda_project.myfluffy.post.model.Post;
import lombok.Getter;

@Getter
public class PostCreatedEvent extends BaseEvent {
    private final Post post;

    public PostCreatedEvent(Object source, Post post) {
        super(source);
        this.post = post;
    }

    @Override
    public void process() {
        System.out.println("Processing Post Created Event");
        // Your specific logic for PostCreatedEvent
    }
}
