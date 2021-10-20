package com.project.xircle;

import java.util.Arrays;
import java.util.List;

public class test {



    public static void main(String[] args){
        List.of("a a a", "b b b", "c c c", "d d d")
                .stream()
                .map(s->change(s))
                .flatMap(s->Arrays.stream(s))
                .forEach(s->System.out.println(s));
    }

    public static String[] change(String s){
        s+="e e e";
        return s.split(" ");
    }
}
