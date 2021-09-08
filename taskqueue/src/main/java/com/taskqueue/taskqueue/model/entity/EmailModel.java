package com.taskqueue.taskqueue.model.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String subject;
    private String body;
    private String receiver;
    private String sender;
}
