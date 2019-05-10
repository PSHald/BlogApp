package keaproject.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(
        basePackages = "keaproject.demo.Model",
        entityManagerFactoryRef = "userEntityManager"
)
public class DBConfig {

    static String connectString = "jdbc:mysql://den1.mysql3.gear.host:3306/pshdb?autoReconnect=true&useSSL=false";
    static String user = "pshdb";
    static String password = "Ar59BgBAR~-e";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager
                .getConnection(connectString, user, password);

        return connect;
    }



}

