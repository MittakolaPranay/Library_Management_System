package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnector() {
        Connection conn = null;
        try {
            Properties props = new Properties();
            InputStream fileReader = DBConnection.class.getClassLoader().getResourceAsStream("config.properties");

            if (fileReader == null) {
                throw new FileNotFoundException("config.properties file not found in classpath");
            }

            props.load(fileReader);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

}

