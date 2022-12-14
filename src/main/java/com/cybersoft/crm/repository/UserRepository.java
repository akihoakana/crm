package com.cybersoft.crm.repository;

import com.cybersoft.crm.DTO.StatusCountDTO;
import com.cybersoft.crm.DTO.UserDetailDTO;
import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.StatusModel;
import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository{
    public List<UserModel> getUsersByUserAndPassword(String email,String password) {
        List<UserModel>list=new ArrayList<>();
        try {
            String query="select * from users u where u.email=? and u.password=?";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,email);
            preparedStatemen.setString(2,password);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                UserModel userModel=new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setFirstname(resultSet.getString("firstname"));
                userModel.setLastname(resultSet.getString("lastname"));
                userModel.setPhone(resultSet.getString("phone"));
                userModel.setCountry(resultSet.getString("country"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setRole_id(resultSet.getInt("role_id"));
                list.add(userModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getUsersByEmailAndPassword"+e.getMessage());

        }
        return list;

    }
    public int getIdByEmail(String email){
        int id=0;
        try {

            String query="SELECT users.id FROM users where users.email = ?";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,email);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                id=resultSet.getInt("id");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getIdByEmail"+e.getMessage());
        }
        return id;
    }
    public List<UserModel> getUsers() {
        List<UserModel>list=new ArrayList<>();
        try {
//            String query="select id,firstname, lastname,username,role_id from users";
            String query="SELECT users.id,users.firstname,users.fullname,users.lastname,users.username,roles.name as rolesname " +
                    " FROM users LEFT JOIN roles ON users.role_id = roles.id";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                UserModel userModel=new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setFirstname(resultSet.getString("firstname"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setLastname(resultSet.getString("lastname"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setRoleName(resultSet.getString("rolesname"));
                list.add(userModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getUsers"+e.getMessage());
        }
        return list;
    }
    public List<TasksModel> getUserTasksById(int id) {
        List<TasksModel>list=new ArrayList<>();
        try {
            String query="SELECT tasks.id,tasks.name,jobs.name as jobsname,users.fullname,users.email,tasks.start_date" +
                    ",tasks.end_date,status.name as statusname" +
                    " FROM tasks "  +
                    " LEFT JOIN jobs"  +
                    " ON tasks.job_id = jobs.id"  +
                    " LEFT JOIN status"  +
                    " ON tasks.status_id = status.id"  +
                    " left JOIN users"  +
                    " ON tasks.user_id = users.id "  +
                    " where users.id= ?   ";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                TasksModel tasksModel=new TasksModel();
                tasksModel.setName(resultSet.getString("name"));
                tasksModel.setId(Integer.parseInt(resultSet.getString("id")));
                tasksModel.setJobsname(resultSet.getString("jobsname"));
                tasksModel.setUsersfullname(resultSet.getString("fullname"));
                tasksModel.setUsersemail(resultSet.getString("email"));
                tasksModel.setStart_date(resultSet.getString("start_date"));
                tasksModel.setEnd_date(resultSet.getString("end_date"));
                tasksModel.setStatusname(resultSet.getString("statusname"));
                list.add(tasksModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getUserTasksById"+e.getMessage());
        }
        return list;
    }
    public int updateUsersByClass(UserModel userModel) {
        int resultSet=0;
        try {
            String query="UPDATE users" +
                    " SET email = ?, password= ?"  +
                    " ,fullname=?,firstname= ?"  +
                    " ,lastname= ?,username= ?"  +
                    " ,role_id=?"  +
                    " WHERE users.id = ?";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,userModel.getEmail());
            preparedStatemen.setString(2,userModel.getPassword());
            preparedStatemen.setString(3,userModel.getFullname());
            preparedStatemen.setString(4,userModel.getFirstname());
            preparedStatemen.setString(5,userModel.getLastname());
            preparedStatemen.setString(6,userModel.getUsername());
            preparedStatemen.setInt(7,userModel.getRole_id());
            preparedStatemen.setInt(8,userModel.getId());
            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updateUsersByClass"+e.getMessage());
        }
        return resultSet;
    }
    public int deleteUsersById(int id) {
        int resultSet=0;
        try {
            String query="delete from users  where users.id=?";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);

            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error deleteUsersById"+e.getMessage());
        }
        return resultSet;
    }
    public int insertUsers(UserModel userModel) {
        int resultSet=0;
        try {
            String query="INSERT INTO users(email,password,fullname,phone,country )" +
                    " VALUES (?,?,?,?,?)";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,userModel.getEmail());
            preparedStatemen.setString(2,userModel.getPassword());
            preparedStatemen.setString(3,userModel.getFullname());
            preparedStatemen.setString(4,userModel.getPhone());
            preparedStatemen.setString(5,userModel.getCountry());
            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error insertUsers"+e.getMessage());
        }
        return resultSet;
    }
    public int updateUsersByTask(TasksModel tasksModel) {
        int resultSet=0;
        try {
            String query="UPDATE tasks" +
                    " SET name = ?, start_date= ?"  +
                    " ,end_date=?,job_id= ?,status_id= ?"  +
                    " WHERE tasks.id = ? and  user_id= ?";
            Connection connection= MysqlConnection.getConnection();
            System.out.println(tasksModel.toString());
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,tasksModel.getName());
            preparedStatemen.setString(2,tasksModel.getStart_date());
            preparedStatemen.setString(3,tasksModel.getEnd_date());
            preparedStatemen.setInt(4,tasksModel.getJob_id());
            preparedStatemen.setInt(5,tasksModel.getStatus_id());
            preparedStatemen.setInt(6,tasksModel.getId());
            preparedStatemen.setInt(7,tasksModel.getUser_id());

            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updateUsersByClass"+e.getMessage());
        }
        return resultSet;
    }
    public StatusCountDTO getQuantityUsersById(int id,int idStatus) {
        StatusCountDTO statusCountDTO=new StatusCountDTO();
        try {
            String query="SELECT count( status.name) as count " +
                    " FROM tasks  "  +
                    " left JOIN status "  +
                    " ON tasks.status_id = status.id "  +
                    " left JOIN users "  +
                    " ON tasks.user_id = users.id  "  +
                    " where users.id= ? and status.id =?";
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
            System.out.println("Error getQuantityUsersById"+e.getMessage());
        }
        return statusCountDTO;
    }
    public List<UserDetailDTO> getTaskUsersByIdAndStatus(int id,int idStatus) {
        List<UserDetailDTO> list=new ArrayList<>();
        try {
            String query=" SELECT tasks.name,tasks.start_date,tasks.end_date"   +
                    " FROM tasks "   +
                    " LEFT JOIN status"   +
                    " ON tasks.status_id = status.id"   +
                    " left JOIN users"   +
                    " ON tasks.user_id = users.id "   +
                    " where users.id=? and status.id =? " ;
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);
            preparedStatemen.setInt(2,idStatus);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                UserDetailDTO userDetailDTO=new UserDetailDTO();
                userDetailDTO.setName(resultSet.getString("name"));
                userDetailDTO.setStart_date(resultSet.getString("start_date"));
                userDetailDTO.setEnd_date(resultSet.getString("end_date"));
                list.add(userDetailDTO);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getTaskUsersByIdAndStatus"+e.getMessage());
        }
        return list;
    }

    public UserDetailDTO getFullnameEmailById(int id) {
        UserDetailDTO userDetailDTO=new UserDetailDTO();
        try {
            String query=" SELECT users.fullname,users.email"   +
                    " FROM users "   +
                    " where users.id=?" ;
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);
            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                userDetailDTO.setUsersemail(resultSet.getString("email"));
                userDetailDTO.setUsersfullname(resultSet.getString("fullname"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getFullnameEmailById"+e.getMessage());
        }
        return userDetailDTO;
    }
}

