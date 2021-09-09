package com.taskqueue.taskqueue.controller;

import com.taskqueue.taskqueue.model.dto.CustomMessage;
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

@RestController
public class ControllerTask {


    @Autowired
    private QueueTask queueTask;


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

    @GetMapping("/chao")
    @ResponseBody
    public String chao() {
        return Arrays.toString(queueTask.getPriorityBlockingQueue().toArray());

    }


}
