package com.cybersoft.crm.repository;

import com.cybersoft.crm.DTO.GroupWorkDetails;
import com.cybersoft.crm.DTO.StatusCountDTO;
import com.cybersoft.crm.DTO.UserDetailDTO;
import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.service.JobService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRepository {
    public List<JobsModel> getJobs() {
        List<JobsModel>list=new ArrayList<>();
        try {
            String query="select * from jobs";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                JobsModel jobsModel=new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStart_date(resultSet.getString("start_date"));
                jobsModel.setEnd_date(resultSet.getString("end_date"));
                list.add(jobsModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getJobs"+e.getMessage());
        }
        return list;

    }
    public int insertJob(String name ,String start_date,String end_date ) {

        int resultSet=0;
        try {
            String query="INSERT INTO jobs( name, start_date,end_date ) VALUES (?,?,?)";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,name);
            preparedStatemen.setString(2,start_date);
            preparedStatemen.setString(3,end_date);
            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error insertJob"+e.getMessage());
        }
        return resultSet;
    }

    public List<TasksModel> getTaskDetailById(int id) {
        List<TasksModel>list=new ArrayList<>();
        try {
            String query="SELECT  tasks.name as tasksname, jobs.name as jobsname,users.fullname,status.name as statusname " +
                     " FROM tasks   "  +
                     " inner JOIN status  "  +
                     " ON tasks.status_id = status.id  "  +
                     " left JOIN users  "  +
                     " ON tasks.user_id = users.id   "  +
                     " LEFT JOIN jobs  "  +
                     " ON tasks.job_id = jobs.id  "  +
                     " where jobs.id= ? " ;
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                TasksModel tasksModel=new TasksModel();
                tasksModel.setName(resultSet.getString("tasksname"));
                tasksModel.setJobsname(resultSet.getString("jobsname"));
                tasksModel.setUsersfullname(resultSet.getString("fullname"));
                tasksModel.setStatusname(resultSet.getString("statusname"));
                list.add(tasksModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getTaskDetailById"+e.getMessage());
        }
        return list;

    }
    public int deleteJobsById(int id) {
        int resultSet=0;
        try {
            String query="delete from jobs  where jobs.id=?";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);

            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error deleteJobsById"+e.getMessage());
        }
        return resultSet;
    }
    public int updateJobsByClass(JobsModel jobsModel) {
        int resultSet=0;
        try {
            String query="UPDATE jobs" +
                    " SET name = ?, start_date= ? ,end_date=?" +
                    " WHERE jobs.id = ? ";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,jobsModel.getName());
            preparedStatemen.setString(2,jobsModel.getStart_date());
            preparedStatemen.setString(3,jobsModel.getEnd_date());
            preparedStatemen.setInt(4,jobsModel.getId());
            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error UpdateJobsByClass"+e.getMessage());
        }
        return resultSet;
    }
    public StatusCountDTO getQuantityJobsById(int id,int idStatus) {
        StatusCountDTO statusCountDTO=new StatusCountDTO();
        try {
            String query=" SELECT  count( status.name) as count"  +
                    "  FROM tasks "  +
                    " left JOIN jobs"  +
                    " ON tasks.job_id = jobs.id"  +
                    " left JOIN status"  +
                    " ON tasks.status_id = status.id"  +
                    " where jobs.id= ? and status.id=?" ;
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);
            preparedStatemen.setInt(2,idStatus);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                statusCountDTO.setCount(resultSet.getInt("count"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getQuantityJobsById"+e.getMessage());
        }
        return statusCountDTO;
    }
    public List<UserDetailDTO> getNameByIdJob(int id) {
        List<UserDetailDTO> list=new ArrayList<>();
        try {
            String query=" SELECT  distinct users.fullname"  +
                    " FROM tasks "  +
                    " left JOIN jobs"  +
                    " ON tasks.job_id = jobs.id"  +
                    " left JOIN users"  +
                    " ON tasks.user_id = users.id "  +
                    " where jobs.id= ?" ;
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                UserDetailDTO userDetailDTO=new UserDetailDTO();
                userDetailDTO.setName(resultSet.getString("fullname"));
                list.add(userDetailDTO);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getTaskUsersByIdAndStatus"+e.getMessage());
        }
        return list;
    }
    public List<GroupWorkDetails> getByIdJob(int id) {
        List<GroupWorkDetails> list=new ArrayList<>();
        try {
            String query="  SELECT users.fullname,tasks.name,tasks.start_date,tasks.end_date,status.name as statusname "  +
                    " FROM tasks  "  +
                    " left JOIN jobs "  +
                    " ON tasks.job_id = jobs.id "  +
                    " left JOIN status "  +
                    " ON tasks.status_id = status.id "  +
                    " left JOIN users "  +
                    " ON tasks.user_id = users.id  "  +
                    " where jobs.id= ?" ;
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                GroupWorkDetails groupWorkDetails=new GroupWorkDetails();
                groupWorkDetails.setUsersfullname(resultSet.getString("fullname"));
                groupWorkDetails.setName(resultSet.getString("name"));
                groupWorkDetails.setStart_date(resultSet.getString("start_date"));
                groupWorkDetails.setEnd_date(resultSet.getString("end_date"));
                groupWorkDetails.setStatusname(resultSet.getString("statusname"));
                list.add(groupWorkDetails);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getByIdJob"+e.getMessage());
        }
        return list;
    }
}
