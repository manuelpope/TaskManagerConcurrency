package com.taskqueue.taskqueue.service.FactoryTask;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import com.taskqueue.taskqueue.service.FactoryTask.process.EmailSender;
import com.taskqueue.taskqueue.service.FactoryTask.process.EmailSenderAlter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        this.mapTaskInstance = new HashMap<>();
        this.mapTaskInstance.put("email", this.emailSender);
        this.mapTaskInstance.put("emailre", this.emailSenderAlter);
    }


    public Task getInstanceOfTask(CustomMessage customMessage) {
        Task t = this.mapTaskInstance.get(customMessage.getType()).buildInstance(customMessage.getId());
        return t;
    }
}
