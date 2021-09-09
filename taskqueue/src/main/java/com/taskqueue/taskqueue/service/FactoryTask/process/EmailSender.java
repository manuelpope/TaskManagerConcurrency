package com.taskqueue.taskqueue.service.FactoryTask.process;

import com.taskqueue.taskqueue.model.IEmailRepository;
import com.taskqueue.taskqueue.model.ISchedulerRepository;
import com.taskqueue.taskqueue.model.entity.EmailModel;
import com.taskqueue.taskqueue.model.entity.SchedulerModel;
import com.taskqueue.taskqueue.service.FactoryTask.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * The type Email sender.
 */
@Component
@Data
@NoArgsConstructor
@Slf4j
public class EmailSender implements Task {


    private EmailModel emailModel;
    @Autowired
    private ISchedulerRepository iSchedulerRepository;
    @Autowired
    private IEmailRepository iEmailRepository;


    @Override
    public synchronized boolean doTask() {

        try {
            Thread.sleep(10000);
            log.info(this.emailModel.toString());
            log.info("update status to true, done ....ID: " + this.emailModel.getId().toString() + "_____" + Thread.currentThread().getName());
            this.updateStatus();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //change status task to done
        return true;
    }

    private void updateStatus() {
        Optional<SchedulerModel> schedulerModel = iSchedulerRepository.findById(this.emailModel.getId());
        SchedulerModel schedulerTask;
        schedulerTask = schedulerModel.get();
        schedulerTask.setDone(true);
        iSchedulerRepository.save(schedulerTask);
    }

    @Override
    public synchronized Task buildInstance(String id) {
        // heres logic to retrieve data , for now just  mocking


        this.emailModel = iEmailRepository.findById(Integer.valueOf(id)).get();

        //logic to recover info of task before someone does it by id

        return this;
    }


}
