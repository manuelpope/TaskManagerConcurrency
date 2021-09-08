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
    public boolean doTask() {
        log.info(this.emailModel.toString());
        log.info("update status to true, done");
        //change status task to done
        return false;
    }

    @Override
    public Task buildInstance(String id) {
        // heres logic to retrieve data , for now just  mocking
        this.emailModel = new EmailModel();
        this.emailModel.setSender("Elon");
        this.emailModel.setId(1);
        this.emailModel.setReceiver("Jeff");
        this.emailModel.setBody("Space is gold");
        this.emailModel.setSubject("No pressure");
        //logic to recover info of task before someone does it by id

        return this;
    }


}
