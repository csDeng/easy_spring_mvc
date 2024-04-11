package com.example.show_bean.a05.component;


import org.springframework.stereotype.Component;

@Component
public class bean2 {
    public bean2(){
        System.out.println(">>>>>>>>>" + bean2.class.getSimpleName() + "spring init");
    }
}
