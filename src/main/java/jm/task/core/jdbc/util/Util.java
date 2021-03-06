package jm.task.core.jdbc.util;



import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USER = "root";
    private static final String PASSWORD = "Pasha19980906";
    private static Connection connection;
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", URL);
            properties.setProperty("hibernate.connection.username", USER);
            properties.setProperty("hibernate.connection.password", PASSWORD);
            properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

            SESSION_FACTORY = new Configuration()
                            .addAnnotatedClass(User.class)
                            .setProperties(properties)
                            .buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
