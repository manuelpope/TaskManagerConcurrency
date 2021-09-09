package com.taskqueue.taskqueue.service.FactoryTask;

/**
 * The interface Task.
 *
 * @param <T> the type parameter
 */
public interface Task<T> {
    /**
     * Do task boolean.
     *
     * @return the boolean
     * @throws Exception the exception
     */
    boolean doTask() throws Exception;

    /**
     * Build instance task.
     *
     * @param id the id
     * @return the task
     */
    Task buildInstance(String id);
}
