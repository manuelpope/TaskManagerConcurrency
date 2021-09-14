package com.taskqueue.taskqueue.service.concurrentmanager;

import com.taskqueue.taskqueue.service.FactoryTask.ManagerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The type Manager pool.
 */
@Service
@Slf4j
public class ManagerPool {

    private final ExecutorService executorService;
    private ExecutorTask executorTask;

    @Autowired
    private QueueTask queueTask;
    @Autowired
    private ManagerBuilder managerBuilder;


    /**
     * Instantiates a new Manager pool.
     *
     * @param queueTask      the queue task
     * @param managerBuilder
     */
    public ManagerPool(QueueTask queueTask, ManagerBuilder managerBuilder) {
        this.queueTask = queueTask;
        this.managerBuilder = managerBuilder;
        this.executorService = Executors.newFixedThreadPool(2);


    }

    /**
     * Daemon execution.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Scheduled(fixedRate = 3000)
    public synchronized void daemonExecution() throws InterruptedException {

        // here query and logic to put elements into the queue remember el take has to be place inside runnable
        //in that way it will perform all tasks

        if (!queueTask.getPriorityBlockingQueue().isEmpty()) {

            List<ExecutorTask> executorTaskList = IntStream.range(0, 3)
                    .mapToObj(s -> new ExecutorTask(queueTask.getPriorityBlockingQueue(), managerBuilder))
                    .collect(Collectors.toList());

            executorTaskList.forEach(service -> {
                try {

                    executorService.execute(service);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info(String.valueOf(queueTask.getPriorityBlockingQueue().toArray().length) + "pendinds");
            });


        } else {
            log.info("no elems in Blocking Queue " + Thread.currentThread().getName());
        }
    }

}
