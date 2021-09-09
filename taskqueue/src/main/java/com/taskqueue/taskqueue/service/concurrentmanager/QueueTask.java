package com.taskqueue.taskqueue.service.concurrentmanager;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * The type Queue task.
 */
@Service
@Data
public class QueueTask {
    private PriorityBlockingQueue<CustomMessage> priorityBlockingQueue = new PriorityBlockingQueue<>();


}
