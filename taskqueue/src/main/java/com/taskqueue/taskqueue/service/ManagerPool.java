package com.taskqueue.taskqueue.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ManagerPool {

    private final ExecutorService executorService;
    private ExecutorTask executorTask;

    @Autowired
    private QueueTask queueTask;


    public ManagerPool(QueueTask queueTask) {
        this.queueTask = queueTask;
        this.executorService = Executors.newFixedThreadPool(2);


    }

    @Scheduled(fixedRate = 3000)
    public void daemonExecution() throws InterruptedException {

        // here query and logic to put elements into the queue remember el take has to be place inside runnable
        //in that way it will perform all tasks

        if (!queueTask.getPriorityBlockingQueue().isEmpty()) {

            List<ExecutorTask> executorTaskList = IntStream.range(0, 3)
                    .mapToObj(s-> new ExecutorTask(queueTask.getPriorityBlockingQueue()))
                    .collect(Collectors.toList());

            executorTaskList.forEach(executorService::execute);

//            ExecutorTask executorTask = new ExecutorTask(queueTask.getPriorityBlockingQueue());
//
//            ExecutorTask executorTask2 = new ExecutorTask(queueTask.getPriorityBlockingQueue());
//
//            executorService.execute(executorTask);
//            executorService.execute(executorTask2);
        } else {
            log.info("no elems in Blocking Queue " + Thread.currentThread().getName());
        }
    }

}
