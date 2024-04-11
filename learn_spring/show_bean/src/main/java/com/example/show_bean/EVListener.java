package com.example.show_bean;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EVListener {
    @EventListener
    public void recv(MyEvent e) {
        System.out.println(("接受到事件: source=" + e.getSource() + "time=" + e.getTimestamp()));
    }
}

