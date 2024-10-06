package com.sda_project.myfluffy.notification.types;

import com.sda_project.myfluffy.common.utils.enums.NotificationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Notification {
    protected String name;
    protected String title;
    protected String message;
    protected NotificationType type;

    public abstract void send();

}
