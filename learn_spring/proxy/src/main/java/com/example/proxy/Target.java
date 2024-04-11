package com.example.proxy;

public class Target {
    public void save() {
        System.out.println("0");
    }

    public void save(int i) {
        System.out.println(i);
    }

    public void save(long l) {
        System.out.println(l);
    }
}
