package com.sda_project.myfluffy.common.observers.events;

import org.springframework.context.ApplicationEvent;

public abstract class BaseEvent extends ApplicationEvent {

    public BaseEvent(Object source) {
        super(source);
    }

    public abstract void process();
}