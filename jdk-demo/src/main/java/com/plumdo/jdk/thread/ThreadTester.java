package com.plumdo.jdk.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wengwh
 * @date 2020/9/3
 */
@Slf4j
public class ThreadTester extends Thread {
    public static void main(String[] args) {

        Thread thread = new ThreadTester();
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                log.info("2222");
            }
        });
        thread1.start();

    }

    @Override
    public void run() {
        log.info("main");
    }


}
