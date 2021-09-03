package com.taskqueue.taskqueue.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.PriorityBlockingQueue;

@AllArgsConstructor
@Slf4j
public class ExecutorTask implements Runnable {


    private PriorityBlockingQueue queueTask;


    @Override
    public void run() {
        try {
            Thread.sleep(100);
            while (!queueTask.isEmpty()) {
                log.info(queueTask.take() + " " + Thread.currentThread().getName());
                log.info("Doing the task --------");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
