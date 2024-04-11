package com.example.show_bean;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class MyEvent extends ApplicationEvent implements Serializable {
    public MyEvent(Object source) {
        super(source);
    }
}
