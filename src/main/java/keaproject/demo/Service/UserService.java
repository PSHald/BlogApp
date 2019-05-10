package keaproject.demo.Service;

import keaproject.demo.Model.User;

import java.sql.SQLException;

public interface UserService {
    User login(String username, String password) throws SQLException, ClassNotFoundException;
    void createUser(User user) throws SQLException, ClassNotFoundException;
    void setAdmin(String username) throws SQLException, ClassNotFoundException;
    User findUser(String username) throws SQLException, ClassNotFoundException;
}
