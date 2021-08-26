package com.example.tools;

import java.util.Random;

public  class MyTool {
    public static int generateRandomNumberTool(int start,int end){
        if (start >= end) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((end - start) + 1) + start;
    }
}
