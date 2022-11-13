package com.cybersoft.crm.service;

import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.UserRepository;

import java.util.List;

public class UserService {
    UserRepository userRepository=new UserRepository();
    public List getAllUsers(){
        return userRepository.getUsers();
    }
    public List getUserAllTasksById(int id){
        return userRepository.getUserTasksById(id);
    }
    public boolean updateUsersServiceByClass(UserModel userModel) {
        int result = userRepository.updateUsersByClass(userModel);
        return (result > 0) ? true : false;
    }
    public boolean updateUsersServiceByTask(TasksModel tasksModel) {
        int result = userRepository.updateUsersByTask(tasksModel);
        return (result > 0) ? true : false;
    }
    public boolean deleteUsersServiceById(int id) {
        int result = userRepository.deleteUsersById(id);
        return (result > 0) ? true : false;
    }
    public boolean insertUsersService(UserModel userModel) {
        int result = userRepository.insertUsers(userModel);
        return (result > 0) ? true : false;
    }
}
