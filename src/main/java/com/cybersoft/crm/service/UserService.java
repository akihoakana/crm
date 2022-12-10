package com.cybersoft.crm.service;

import com.cybersoft.crm.DTO.StatusCountDTO;
import com.cybersoft.crm.DTO.UserDetailDTO;
import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.UserRepository;

import java.util.List;

public class UserService {
    UserRepository userRepository=new UserRepository();
    public int getIdByEmailService(String email){
        return userRepository.getIdByEmail(email);
    }

    public List getAllUsers(){
        return userRepository.getUsers();
    }
    public StatusCountDTO getQuantityUsersById(int id,int idStatus){
        int count1=userRepository.getQuantityUsersById(id,1).getCount();
        int count2=userRepository.getQuantityUsersById(id,2).getCount();
        int count3=userRepository.getQuantityUsersById(id,3).getCount();
        StatusCountDTO statusCountDTO =userRepository.getQuantityUsersById(id,idStatus);
        if ((count1+count2+count3)==0){
            statusCountDTO.setCountPercent(0);
        }
        else {
            statusCountDTO.setCountPercent(statusCountDTO.getCount()*100/(count1+count2+count3));
        }
        return statusCountDTO;
    }
    public UserDetailDTO getFullnameEmailByIdService(int id){
        return userRepository.getFullnameEmailById(id);
    }
    public List getUserAllTasksById(int id){
        return userRepository.getUserTasksById(id);
    }
    public List<UserDetailDTO> getTaskUsersByIdAndStatusService(int id, int idStatus){
        return userRepository.getTaskUsersByIdAndStatus(id,idStatus);
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
