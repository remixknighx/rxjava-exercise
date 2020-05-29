package com.bill.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianfeng
 */
public class TestParallel {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            list.add(i);
        }

        long startTime = System.currentTimeMillis();
        list.parallelStream().forEachOrdered(System.out::println);
        System.out.println("Cost time:" + (System.currentTimeMillis() - startTime));
    }

}
