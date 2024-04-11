package com.example.aspect_ajc;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 没有 使用 @Component spring 管理
 */
@Aspect
public class MyAspect {

    @Before("execution(* com.example.aspect_ajc.MyService.foo())")
    public void before() {
        System.out.println("before()");
    }
}
