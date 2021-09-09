package com.taskqueue.taskqueue.model;

import com.taskqueue.taskqueue.model.entity.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface IEmailRepository extends JpaRepository<EmailModel, Integer> {


}