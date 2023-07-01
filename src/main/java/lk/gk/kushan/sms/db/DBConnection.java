package lk.gk.kushan.sms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static DBConnection instance;
    private final Connection connection;


    private DBConnection() {
        try {
            connection= DriverManager.getConnection("jdbc:mysql://dep10.lk:3306/dep10_jdbc","root","mysql");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return instance == null ? new DBConnection() : instance;
    }

    public  Connection getConnection() {
        return connection;
    }

}
