package db;

import config.AppConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectorDB {

    static Connection getConnection(AppConfiguration cfg) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(cfg.URL, AppConfiguration.USER, AppConfiguration.PASS);
    }
}
