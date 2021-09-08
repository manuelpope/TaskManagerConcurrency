package com.taskqueue.taskqueue.service.concurrentmanager;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

@Service
@Data
public class QueueTask {
    private PriorityBlockingQueue<CustomMessage> priorityBlockingQueue = new PriorityBlockingQueue<>();


}
