package com.taskqueue.taskqueue.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Email model.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "EMAIL")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject;
    private String body;
    private String receiver;
    private String sender;
}
