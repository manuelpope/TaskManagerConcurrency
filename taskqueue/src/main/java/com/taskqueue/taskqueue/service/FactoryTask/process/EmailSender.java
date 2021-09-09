package com.taskqueue.taskqueue.service.FactoryTask.process;

import com.taskqueue.taskqueue.model.entity.EmailModel;
import com.taskqueue.taskqueue.service.FactoryTask.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
@Slf4j
public class EmailSender implements Task {

    //private repository
    private EmailModel emailModel;


    @Override
    public synchronized boolean doTask() {

        try {
            Thread.sleep(10000);
            log.info(this.emailModel.toString());
            log.info("update status to true, done ....ID: " + this.emailModel.getId().toString() + "_____" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //change status task to done
        return false;
    }

    @Override
    public synchronized Task buildInstance(String id) {
        // heres logic to retrieve data , for now just  mocking
        this.emailModel = new EmailModel();
        this.emailModel.setSender("Elon");
        this.emailModel.setId(Integer.valueOf(id));
        this.emailModel.setReceiver("Jeff");
        this.emailModel.setBody("Space is gold");
        this.emailModel.setSubject("No pressure");
        //logic to recover info of task before someone does it by id

        return this;
    }


}
