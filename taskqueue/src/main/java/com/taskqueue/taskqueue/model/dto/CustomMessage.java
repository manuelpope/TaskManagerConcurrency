package com.taskqueue.taskqueue.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class CustomMessage implements Comparable<CustomMessage> {

    private String id;
    private String type;


    @Override
    public int compareTo(CustomMessage customMessage) {
        return 0;
    }
}
