package com.cybersoft.crm.service;

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

}
