package com.taskqueue.taskqueue.controller;

import com.taskqueue.taskqueue.service.QueueTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ControllerTask {


    @Autowired
    private QueueTask queueTask;


    @GetMapping("/hola")
    @ResponseBody
    public String hola() {
        queueTask.getPriorityBlockingQueue().put("a");
        return "has hecho una peticion get";

    }

    @GetMapping("/chao")
    @ResponseBody
    public String chao() {
        return Arrays.toString(queueTask.getPriorityBlockingQueue().toArray());

    }


}
