package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cybersoft.crm.model.RoleModel;

public class RoleRepository {
    public List<RoleModel> getRoles() {
        List<RoleModel>list=new ArrayList<>();
        try {
            String query="select * from roles";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);

            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                RoleModel roleModel=new RoleModel();
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));
                list.add(roleModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getRolesByEmailAndPassword"+e.getMessage());

        }
        return list;

    }
    public int deleteRolesById(int id) {
        int resultSet=0;
        try {
            String query="delete from roles r where r.id=?";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setInt(1,id);

            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error deleteRolesById"+e.getMessage());
        }
        return resultSet;
    }
    public int insertRole(String name ,String description ) {

        int resultSet=0;
        try {
            String query="INSERT INTO roles( name, description ) VALUES (?,?)";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,name);
            preparedStatemen.setString(2,description);
            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error insertRole"+e.getMessage());
        }
        return resultSet;
    }
    public int UpdateRoleById(RoleModel roleModel) {
        int resultSet=0;
        try {
            String query="UPDATE roles" +
                    " SET name = ?, description= ?" +
                    " WHERE roles.id = ? ";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);
            preparedStatemen.setString(1,roleModel.getName());
            preparedStatemen.setString(2,roleModel.getDescription());
            preparedStatemen.setInt(3,roleModel.getId());

            resultSet= preparedStatemen.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error UpdateById"+e.getMessage());
        }
        return resultSet;
    }
}
