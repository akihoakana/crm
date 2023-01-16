package com.cybersoft.crm.service;

import com.cybersoft.crm.DTO.GroupWorkDetailsDTO;
import com.cybersoft.crm.DTO.StatusCountDTO;
import com.cybersoft.crm.DTO.UserDetailDTO;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.repository.JobRepository;

import java.util.List;

public class JobService {
    JobRepository jobRepository=new JobRepository();
    public List getAllJobs(){
        return jobRepository.getJobs();
    }
    public List getTaskServiceDetailById(int id){
        return jobRepository.getTaskDetailById(id);
    }
    public boolean insertJobService(String name ,String start_date,String end_date ){
        int result=jobRepository.insertJob(name,start_date,end_date);
        if (result>0){
            return true;
        }
        else
            return false;
    }
    public boolean updateJobsServiceByClass(JobsModel jobsModel){
        int result=jobRepository.updateJobsByClass(jobsModel);
        return (result>0)?true:false;
    }
    public boolean deleteJobsServiceById(int id){
        int result=jobRepository.deleteJobsById(id);
        return (result>0)?true:false;
    }
    public StatusCountDTO getQuantityJobsById(int id, int idStatus){
        int count1=jobRepository.getQuantityJobsById(id,1).getCount();
        int count2=jobRepository.getQuantityJobsById(id,2).getCount();
        int count3=jobRepository.getQuantityJobsById(id,3).getCount();
        StatusCountDTO statusCountDTO =jobRepository.getQuantityJobsById(id,idStatus);
        if ((count1+count2+count3)==0){
            statusCountDTO.setCountPercent(0);
        }
        else {
            statusCountDTO.setCountPercent(statusCountDTO.getCount()*100/(count1+count2+count3));
        }
        return statusCountDTO;
    }
    public List<UserDetailDTO> getNameByIdJobService(int id) {
        return jobRepository.getNameByIdJob(id);
    }


}
