package com.taskqueue.taskqueue.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * The type Scheduler model.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulerModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String type;
    private String description;
    private boolean done;
}
