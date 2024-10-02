package com.sda_project.myfluffy.common.observers.factory;

import com.sda_project.myfluffy.common.observers.events.BaseEvent;

public interface EventFactory {
    BaseEvent createStatusChangeEvent(Object source, Object entity);
    BaseEvent createFounderChangeEvent(Object source, Object entity);
    BaseEvent createPostCreatedEvent(Object source, Object entity);
}
