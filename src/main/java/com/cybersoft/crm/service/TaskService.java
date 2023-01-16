package com.cybersoft.crm.service;

import com.cybersoft.crm.DTO.GroupWorkDetailsDTO;
import com.cybersoft.crm.model.StatusGroupWorkDetailsModelModel;
import com.cybersoft.crm.model.StatusModel;
import com.cybersoft.crm.model.TaskGroupWorkDetailsModel;
import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.repository.JobRepository;
import com.cybersoft.crm.repository.StatusRepository;
import com.cybersoft.crm.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();
    StatusRepository statusRepository = new StatusRepository();

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

    public List<GroupWorkDetailsDTO> getGroupWorkDetailsByJobId(int jobjd) {
        List<GroupWorkDetailsDTO> list = taskRepository.getUsersByJobId(jobjd);
        for (GroupWorkDetailsDTO groupWorkDetailsDTO: list)
        {
            List<StatusGroupWorkDetailsModelModel> list1 = new ArrayList<>();
            for (StatusModel statusModel: statusRepository.getStatus()){
                StatusGroupWorkDetailsModelModel statusGroupWorkDetailsModelModel=new StatusGroupWorkDetailsModelModel();
                statusGroupWorkDetailsModelModel.setId(statusModel.getId());
                statusGroupWorkDetailsModelModel.setNameStatus(statusModel.getName());
                list1.add(statusGroupWorkDetailsModelModel);
            }
            groupWorkDetailsDTO.setStatusGroupWorkDetailsModelModels(list1);
            for (StatusGroupWorkDetailsModelModel statusGroupWorkDetailsModelModel: groupWorkDetailsDTO.getStatusGroupWorkDetailsModelModels()){
                statusGroupWorkDetailsModelModel.setTaskGroupWorkDetailsModels(taskRepository.getTaskByJobIdAndUserIdAndStatusId(jobjd,groupWorkDetailsDTO.getId(),statusGroupWorkDetailsModelModel.getId()));
                groupWorkDetailsDTO.setStatusGroupWorkDetailsModelModels(list1);
            }
        }
        return list;
    }
}
