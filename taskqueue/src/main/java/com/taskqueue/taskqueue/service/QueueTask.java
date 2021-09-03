package com.taskqueue.taskqueue.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

@Service
@Data
public class QueueTask {
    private PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();


}
