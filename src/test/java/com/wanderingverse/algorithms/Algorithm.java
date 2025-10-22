package com.wanderingverse.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lihui
 * @since 2025/9/25 16:47
 **/
@Slf4j
public class Algorithm {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        // 输出当前线程名：main
    }


    /**
     * 累加从 start 到 end 的和
     * <p>递归法
     *
     * @param start long
     * @param end   long
     * @return long
     */
    public long sumRangeRecursive(long start, long end) {
        log.info("start: {}, end: {}", start, end);
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return end;
        } else {
            return start + sumRangeRecursive(start + 1, end);
        }
    }
}