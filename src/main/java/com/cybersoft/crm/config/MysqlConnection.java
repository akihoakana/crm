package com.cybersoft.crm.config;

import java.sql.Connection;
import java.sql.DriverManager;


public class MysqlConnection {


    public static Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3309/crm_app",
                    "root", "112233");
        } catch (Exception e) {
            System.out.println("Error connect db"+e.getMessage());
        }
        return connection;

    }
}
