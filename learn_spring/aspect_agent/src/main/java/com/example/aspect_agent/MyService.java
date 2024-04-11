package com.example.aspect_agent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void foo() {
        System.out.println("foo()");
        bar();
    }


    public void bar() {
        System.out.println("bar()");
    }
}
