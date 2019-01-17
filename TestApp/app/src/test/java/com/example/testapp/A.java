package com.example.testapp;

public abstract class A extends C implements B {
    @Override
    public void c() {
        System.out.println("A:c");
        super.c();
    }
}
