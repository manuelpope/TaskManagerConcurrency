package com.taskqueue.taskqueue.service.FactoryTask;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import com.taskqueue.taskqueue.service.FactoryTask.process.EmailSender;
import com.taskqueue.taskqueue.service.FactoryTask.process.EmailSenderAlter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Setter
public class ManagerBuilder {
    //map string and supplier to being a factory of same interface
    private Map<String, Task> mapTaskInstance;
    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailSenderAlter emailSenderAlter;

    public ManagerBuilder(EmailSender emailSender, EmailSenderAlter emailSenderAlter) {
        this.emailSender = emailSender;
        this.emailSenderAlter = emailSenderAlter;
        this.mapTaskInstance = new ConcurrentHashMap<>();
        this.mapTaskInstance.put("email", this.emailSender);
        this.mapTaskInstance.put("emailre", this.emailSenderAlter);
    }


    public synchronized Task getInstanceOfTask(CustomMessage customMessage) {
        Task t = this.mapTaskInstance.get(customMessage.getType()).buildInstance(customMessage.getId());
        System.out.println("task:" + t);
        return t;
    }
}
