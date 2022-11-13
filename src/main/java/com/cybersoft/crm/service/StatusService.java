package com.cybersoft.crm.service;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.StatusModel;
import com.cybersoft.crm.repository.RoleRepository;
import com.cybersoft.crm.repository.StatusRepository;

import java.util.List;

public class StatusService {
    private StatusRepository statusRepository=new StatusRepository();
    public List<StatusModel> getStatusService(){
        return statusRepository.getStatus();
    }
}
