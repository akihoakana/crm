package com.cybersoft.crm.model;

public class TasksModel {
    private int id;
    private String name;
    private String start_date;
    private String end_date;
    private int user_id;
    private int job_id;
    private int status_id;
    private String jobsname;
    private String usersfullname;
    private String usersemail;
    private String statusname;

    public String getUsersemail() {
        return usersemail;
    }

    public void setUsersemail(String usersemail) {
        this.usersemail = usersemail;
    }

    public String getJobsname() {
        return jobsname;
    }

    public void setJobsname(String jobsname) {
        this.jobsname = jobsname;
    }

    public String getUsersfullname() {
        return usersfullname;
    }

    public void setUsersfullname(String usersfullname) {
        this.usersfullname = usersfullname;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "TasksModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", user_id=" + user_id +
                ", job_id=" + job_id +
                ", status_id=" + status_id +
                ", jobsname='" + jobsname + '\'' +
                ", usersfullname='" + usersfullname + '\'' +
                ", usersemail='" + usersemail + '\'' +
                ", statusname='" + statusname + '\'' +
                '}';
    }
}
