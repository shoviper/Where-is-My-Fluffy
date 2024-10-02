package com.sda_project.myfluffy.common.observers.factory.post;

import com.sda_project.myfluffy.common.observers.events.BaseEvent;
import com.sda_project.myfluffy.common.observers.events.post.PostCreatedEvent;
import com.sda_project.myfluffy.common.observers.factory.EventFactory;
import com.sda_project.myfluffy.post.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostEventFactory implements EventFactory {

    @Override
    public BaseEvent createStatusChangeEvent(Object source, Object entity) {
        throw new UnsupportedOperationException("Status change event not supported in PostEventFactory");
    }

    @Override
    public BaseEvent createFounderChangeEvent(Object source, Object entity) {
        throw new UnsupportedOperationException("Founder change event not supported in PostEventFactory");
    }

    @Override
    public BaseEvent createPostCreatedEvent(Object source, Object entity) {
        return new PostCreatedEvent(source, (Post) entity);
    }
}