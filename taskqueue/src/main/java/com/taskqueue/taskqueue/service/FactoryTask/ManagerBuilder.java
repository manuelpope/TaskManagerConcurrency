package com.taskqueue.taskqueue.service.FactoryTask;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import com.taskqueue.taskqueue.service.FactoryTask.process.EmailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ManagerBuilder {
    //map string and supplier to being a factory of same interface
    private Map<String, Task> mapTaskInstance;


    public ManagerBuilder() {
        this.mapTaskInstance = new HashMap<>();
        this.mapTaskInstance.put("a", new EmailSender());

    }

    public Task getInstanceOfTask(CustomMessage customMessage) {
        Task t = this.mapTaskInstance.get(customMessage.getType()).buildInstance(customMessage.getId());
        return t;
    }
}
