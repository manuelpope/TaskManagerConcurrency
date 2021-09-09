package com.taskqueue.taskqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The type Taskqueue application.
 */
@SpringBootApplication
@EnableScheduling
public class TaskqueueApplication {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TaskqueueApplication.class, args);
    }

}
