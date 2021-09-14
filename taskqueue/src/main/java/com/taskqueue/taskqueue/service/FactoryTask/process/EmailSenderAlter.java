package com.taskqueue.taskqueue.service.FactoryTask.process;

import com.taskqueue.taskqueue.model.entity.EmailModel;
import com.taskqueue.taskqueue.model.entity.SchedulerModel;
import com.taskqueue.taskqueue.repository.ISchedulerRepository;
import com.taskqueue.taskqueue.service.FactoryTask.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * The type Email sender alter.
 */
@Component
@Data
@NoArgsConstructor
@Slf4j
public class EmailSenderAlter implements Task {


    //private repository
    private EmailModel emailModel;

    @Autowired
    private ISchedulerRepository iSchedulerRepository;

    @Override
    public synchronized boolean doTask() {


        try {
            Thread.sleep(1000);
            log.info(this.emailModel.toString());
            log.info("update status to true, done ....ID: " + this.emailModel.getId().toString() + "_____" + Thread.currentThread().getName());

            this.updateStatusTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateStatusTask() {
        Optional<SchedulerModel> schedulerModel = iSchedulerRepository.findById(this.emailModel.getId());
        SchedulerModel schedulerTask;
        schedulerTask = schedulerModel.get();
        schedulerTask.setDone(true);
        iSchedulerRepository.save(schedulerTask);
    }

    @Override
    public synchronized Task buildInstance(String id) {
        // heres logic to retrieve data , for now just  mocking
        this.emailModel = new EmailModel();
        this.emailModel.setSender("jeff");
        this.emailModel.setId(Integer.parseInt(id));
        this.emailModel.setReceiver("Elon");
        this.emailModel.setBody("Space is mine");
        this.emailModel.setSubject("No pressure RE");


        return this;
    }


}
