package com.taskqueue.taskqueue.service.FactoryTask;

public interface Task<T> {
    boolean doTask() throws Exception;

    Task buildInstance(String id);
}
