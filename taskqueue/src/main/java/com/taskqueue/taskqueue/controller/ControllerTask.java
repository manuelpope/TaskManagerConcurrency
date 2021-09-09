package com.taskqueue.taskqueue.controller;

import com.taskqueue.taskqueue.model.IEmailRepository;
import com.taskqueue.taskqueue.model.ISchedulerRepository;
import com.taskqueue.taskqueue.model.dto.CustomMessage;
import com.taskqueue.taskqueue.model.entity.EmailModel;
import com.taskqueue.taskqueue.service.concurrentmanager.QueueTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The type Controller task.
 */
@RestController
public class ControllerTask {


    @Autowired
    private QueueTask queueTask;
    @Autowired
    private ISchedulerRepository iSchedulerRepository;
    @Autowired
    private IEmailRepository iEmailRepository;

    /**
     * Hola string.
     *
     * @return the string
     */
    @GetMapping("/hola")
    @ResponseBody
    public String hola() {
        Random rand = new Random(); //ins
        List<String> stringList = Arrays.asList("email", "emailre");
        List<CustomMessage> executorTaskList = IntStream.range(0, 5)
                .mapToObj(s -> CustomMessage.builder().id(String.valueOf(s)).type(stringList.get(rand.nextInt(2))).build())
                .collect(Collectors.toList());
        executorTaskList.add(CustomMessage.builder().id(String.valueOf(7)).type("emailre").build());

        executorTaskList.forEach((i) -> {
            System.out.println(i.toString());
            queueTask.getPriorityBlockingQueue().put(i);
        });


        return "has hecho una peticion get";

    }

    /**
     * Chao string.
     *
     * @return the string
     */
    @GetMapping("/chao")
    @ResponseBody
    public String chao() {
        return Arrays.toString(queueTask.getPriorityBlockingQueue().toArray());

    }

    @GetMapping("/queue")
    @ResponseBody
    public String generateStream() {
        Random rand = new Random(); //ins
        List<String> stringList = Arrays.asList("email", "emailre");


        EmailModel emailModel = new EmailModel();
        emailModel.setSubject("none");
        emailModel.setSender("asa");
        emailModel.setReceiver("none");
        emailModel.setBody("none");
        emailModel.setDone(false);
        emailModel.setDescription("dummy");
        emailModel.setType(stringList.get(rand.nextInt(2)));

        iEmailRepository.save(emailModel);

        queueTask.getPriorityBlockingQueue()
                .put(CustomMessage
                        .builder()
                        .id(String.valueOf(emailModel.getId()))
                        .type(emailModel.getType())
                        .build());


        return "has hecho una peticion para encolar el mensaje";

    }


}
