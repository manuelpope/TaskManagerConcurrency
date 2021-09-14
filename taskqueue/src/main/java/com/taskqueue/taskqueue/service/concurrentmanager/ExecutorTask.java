package com.taskqueue.taskqueue.service.concurrentmanager;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import com.taskqueue.taskqueue.service.FactoryTask.ManagerBuilder;
import com.taskqueue.taskqueue.service.FactoryTask.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * The type Executor task.
 */
@AllArgsConstructor
@Slf4j
public class ExecutorTask implements Runnable {


    private PriorityBlockingQueue<CustomMessage> queueTask;


    @Override
    public void run() {
        try {
            while (!queueTask.isEmpty()) {

                CustomMessage queueVal = queueTask.take();
                System.out.println(queueVal + " " + Thread.currentThread().getName());
                Task flag = ManagerBuilder.getInstanceOfTask(queueVal);
                log.info(flag.doTask() + " " + Thread.currentThread().getName());

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
