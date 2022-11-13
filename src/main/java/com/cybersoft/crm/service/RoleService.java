package com.cybersoft.crm.service;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.UserRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository=new RoleRepository();
    public List<RoleModel> getAllRoles(){
        return roleRepository.getRoles();
    }
    public boolean deleteRolesById(int id){
        int result=roleRepository.deleteRolesById(id);
        if (result>0){
            return true;
        }
        else
            return false;
    }
    public boolean insertRoleService(String name ,String description ){
        int result=roleRepository.insertRole(name,description);
        if (result>0){
            return true;
        }
        else
            return false;
    }
    public boolean UpdateRoleServiceById(RoleModel roleModel){
        int result=roleRepository.UpdateRoleById(roleModel);
        if (result>0){
            return true;
        }
        else
            return false;
    }
}
