package com.spring.maven.test01.proxy;

public class CountServiceImpl implements CountService {   
    private int count = 0;   
       
    public int count() {   
        return ++count;   
    }   
}  