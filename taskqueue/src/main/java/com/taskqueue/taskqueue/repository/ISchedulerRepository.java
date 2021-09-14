package com.taskqueue.taskqueue.repository;

import com.taskqueue.taskqueue.model.entity.SchedulerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface ISchedulerRepository extends JpaRepository<SchedulerModel, Integer> {


}