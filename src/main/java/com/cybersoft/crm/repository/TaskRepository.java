package com.cybersoft.crm.repository;

import com.cybersoft.crm.DTO.GroupWorkDetailsDTO;
import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.TaskGroupWorkDetailsModel;
import com.cybersoft.crm.model.TasksModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<TasksModel> getTasks() {
        List<TasksModel> list = new ArrayList<>();
        try {
//            String query="select * from tasks";
            String query = "SELECT tasks.id,jobs.id as job_id,tasks.name,jobs.name as jobsname,users.fullname,tasks.start_date" +
                    ",tasks.end_date,status.name as statusname" +
                    " FROM tasks" +
                    " LEFT JOIN jobs" +
                    " ON tasks.job_id = jobs.id" +
                    " LEFT JOIN users" +
                    " ON tasks.user_id = users.id" +
                    " LEFT JOIN status" +
                    " ON tasks.status_id = status.id";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatemen.executeQuery();
            while (resultSet.next()) {
                TasksModel tasksModel = new TasksModel();

                tasksModel.setId(resultSet.getInt("id"));
                tasksModel.setName(resultSet.getString("name"));
                tasksModel.setStart_date(resultSet.getString("start_date"));
                tasksModel.setEnd_date(resultSet.getString("end_date"));
                tasksModel.setJobsname(resultSet.getString("jobsname"));
                tasksModel.setUsersfullname(resultSet.getString("fullname"));
                tasksModel.setStatusname(resultSet.getString("statusname"));

//                tasksModel.setUser_id(resultSet.getInt("user_id"));
                tasksModel.setJob_id(resultSet.getInt("job_id"));
//                tasksModel.setStatus_id(resultSet.getInt("status_id"));
                list.add(tasksModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getTasks" + e.getMessage());
        }
        return list;

    }

    public int insertTask(String name, String start_date, String end_date, int user_id, int job_id, int status_id) {

        int resultSet = 0;
        try {
            String query = "INSERT INTO tasks( name, start_date,end_date ,user_id,job_id,status_id ) VALUES (?,?,?,?,?,?)";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            preparedStatemen.setString(1, name);
            preparedStatemen.setString(2, start_date);
            preparedStatemen.setString(3, end_date);
            preparedStatemen.setInt(4, user_id);
            preparedStatemen.setInt(5, job_id);
            preparedStatemen.setInt(6, status_id);
            resultSet = preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error insertTask" + e.getMessage());
        }
        return resultSet;
    }

    public int updateTasksByClass(TasksModel tasksModel) {
        int resultSet = 0;
        try {
            String query = "UPDATE tasks" +
                    " SET name = ?, start_date= ?" +
                    " ,end_date=?,user_id= ?,job_id= ?,status_id= ?" +
                    " WHERE tasks.id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            preparedStatemen.setString(1, tasksModel.getName());
            preparedStatemen.setString(2, tasksModel.getStart_date());
            preparedStatemen.setString(3, tasksModel.getEnd_date());
            preparedStatemen.setInt(4, tasksModel.getUser_id());
            preparedStatemen.setInt(5, tasksModel.getJob_id());
            preparedStatemen.setInt(6, tasksModel.getStatus_id());
            preparedStatemen.setInt(7, tasksModel.getId());
            resultSet = preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updateTasksByClass" + e.getMessage());
        }
        return resultSet;
    }

    public int deleteTasksById(int id) {
        int resultSet = 0;
        try {
            String query = "delete from tasks  where tasks.id=?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            preparedStatemen.setInt(1, id);

            resultSet = preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error deleteTasksById" + e.getMessage());
        }
        return resultSet;
    }

    public List<GroupWorkDetailsDTO> getUsersByJobId(int jobjd) {
        List<GroupWorkDetailsDTO> list = new ArrayList<>();
        try {
            String query = "SELECT users.id,users.fullname " +
                    " FROM tasks  " +
                    " left JOIN jobs " +
                    " ON tasks.job_id = jobs.id " +
                    " left JOIN users " +
                    " ON tasks.user_id = users.id " +
                    " where jobs.id= ?  " +
                    " group by users.id,users.fullname";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            preparedStatemen.setInt(1, jobjd);
            ResultSet resultSet = preparedStatemen.executeQuery();
            while (resultSet.next()) {
                GroupWorkDetailsDTO groupWorkDetailsDTO = new GroupWorkDetailsDTO();
                groupWorkDetailsDTO.setId(resultSet.getInt("id"));
                groupWorkDetailsDTO.setFullname(resultSet.getString("fullname"));
                list.add(groupWorkDetailsDTO);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getTasks" + e.getMessage());
        }
        return list;
    }
    public List<TaskGroupWorkDetailsModel> getTaskByJobIdAndUserIdAndStatusId(int job_id,int user_id,int status_id) {
        List<TaskGroupWorkDetailsModel> list = new ArrayList<>();
        try {
            String query = "SELECT tasks.id,tasks.name,tasks.start_date,tasks.end_date " +
                    " FROM tasks  "  +
                    " left JOIN jobs "  +
                    " ON tasks.job_id = jobs.id "  +
                    " left JOIN users "  +
                    " ON tasks.user_id = users.id "  +
                    " left JOIN status "  +
                    " ON tasks.status_id = status.id "  +
                    " where jobs.id= ? and status.id=? and users.id=? "  +
                    " group by tasks.id,tasks.name,tasks.start_date,tasks.end_date " ;
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            preparedStatemen.setInt(1, job_id);
            preparedStatemen.setInt(2, status_id);
            preparedStatemen.setInt(3, user_id);
            ResultSet resultSet = preparedStatemen.executeQuery();
            while (resultSet.next()) {
                TaskGroupWorkDetailsModel taskGroupWorkDetailsModel = new TaskGroupWorkDetailsModel();
                taskGroupWorkDetailsModel.setId(resultSet.getInt("id"));
                taskGroupWorkDetailsModel.setNameTask(resultSet.getString("name"));
                taskGroupWorkDetailsModel.setStart_date(resultSet.getString("start_date"));
                taskGroupWorkDetailsModel.setEnd_date(resultSet.getString("end_date"));
                list.add(taskGroupWorkDetailsModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getTasks" + e.getMessage());
        }
        return list;
    }
}

