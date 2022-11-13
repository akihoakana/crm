package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.UserRepository;

import java.util.List;

public class LoginService {
    UserRepository userRepository=new UserRepository();
    public boolean checkLogin(String username,String password){
        List<UserModel> list=userRepository.getUsersByUserAndPassword(username,password);
    if (list.size()>0){
            return true;
        }
        else
            return false;

    }
}
