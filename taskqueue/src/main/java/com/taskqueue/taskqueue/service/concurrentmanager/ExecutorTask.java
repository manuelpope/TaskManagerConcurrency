package com.taskqueue.taskqueue.service.concurrentmanager;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import com.taskqueue.taskqueue.service.FactoryTask.ManagerBuilder;
import com.taskqueue.taskqueue.service.FactoryTask.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.PriorityBlockingQueue;

@AllArgsConstructor
@Slf4j
public class ExecutorTask implements Runnable {


    private PriorityBlockingQueue<CustomMessage> queueTask;
    private ManagerBuilder managerBuilder;


    @Override
    public void run() {
        try {
            Thread.sleep(100);

            while (!queueTask.isEmpty()) {
                CustomMessage queueVal = queueTask.take();
                Task flag = managerBuilder.getInstanceOfTask(queueVal);
                log.info(flag.doTask() + " " + Thread.currentThread().getName());
                log.info("pending tasks: " + queueTask.size());

                /* Todo
                recover id and type x repo, factory return class builder or new instance class of type x,
                do task()
                do the magic [strategy , factory, chain Responsibility] pattern to handle the request of type of tasks

                */
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
