package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.model.StatusModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {

    public List<StatusModel> getStatus() {
        List<StatusModel>list=new ArrayList<>();
        try {
            String query="select * from status";
            Connection connection= MysqlConnection.getConnection();
            PreparedStatement preparedStatemen= connection.prepareStatement(query);

            ResultSet resultSet= preparedStatemen.executeQuery();
            while (resultSet.next()){
                StatusModel statusModel=new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));
                list.add(statusModel);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getStatus"+e.getMessage());

        }
        return list;

    }
}