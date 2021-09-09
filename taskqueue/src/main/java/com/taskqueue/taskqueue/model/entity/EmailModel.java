package com.taskqueue.taskqueue.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Email model.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "EMAIL")
public class EmailModel extends SchedulerModel {

    private String subject;
    private String body;
    private String receiver;
    private String sender;
}
