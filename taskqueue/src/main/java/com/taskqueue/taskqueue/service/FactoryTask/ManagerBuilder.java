package com.taskqueue.taskqueue.service.FactoryTask;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
import com.taskqueue.taskqueue.service.FactoryTask.process.EmailSender;
import com.taskqueue.taskqueue.service.FactoryTask.process.EmailSenderAlter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Manager builder.
 */
@Component
@Setter
public class ManagerBuilder {
    //map string and supplier to being a factory of same interface
    private static Map<String, Task> mapTaskInstance;
    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailSenderAlter emailSenderAlter;

    /**
     * Instantiates a new Manager builder.
     *
     * @param emailSender      the email sender
     * @param emailSenderAlter the email sender alter
     */
    public ManagerBuilder(EmailSender emailSender, EmailSenderAlter emailSenderAlter) {
        this.emailSender = emailSender;
        this.emailSenderAlter = emailSenderAlter;
        mapTaskInstance = new ConcurrentHashMap<>();
        mapTaskInstance.put("email", this.emailSender);
        mapTaskInstance.put("emailre", this.emailSenderAlter);
    }


    /**
     * Gets instance of task.
     *
     * @param customMessage the custom message
     * @return the instance of task
     */
    public synchronized static Task getInstanceOfTask(CustomMessage customMessage) {
        Task t = mapTaskInstance.get(customMessage.getType()).buildInstance(customMessage.getId());
        System.out.println("task:" + t);
        return t;
    }
}
