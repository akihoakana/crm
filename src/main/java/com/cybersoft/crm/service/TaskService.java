package com.cybersoft.crm.service;

import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.repository.JobRepository;
import com.cybersoft.crm.repository.TaskRepository;

import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();

    public List getAllTasks() {
        return taskRepository.getTasks();
    }

    public boolean insertTaskService(String name, String start_date, String end_date, int user_id, int job_id,int status_id) {
        int result = taskRepository.insertTask(name, start_date, end_date, user_id, job_id,status_id);
        if (result > 0) {
            return true;
        } else
            return false;
    }

    public boolean updateTasksServiceByClass(TasksModel tasksModel) {
        int result = taskRepository.updateTasksByClass(tasksModel);
        return (result > 0) ? true : false;
    }

    public boolean deleteTasksServiceById(int id) {
        int result = taskRepository.deleteTasksById(id);
        return (result > 0) ? true : false;
    }
}
