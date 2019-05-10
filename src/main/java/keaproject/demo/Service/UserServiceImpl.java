package keaproject.demo.Service;

import keaproject.demo.Model.User;
import keaproject.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserRepository userRepository;

    public UserServiceImpl(){
    }

    @Override
    public User login(String username, String password) throws SQLException, ClassNotFoundException {
        User user = userRepository.findUser(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void createUser(User user) throws SQLException, ClassNotFoundException {
        userRepository.insertUsertoDB(
                user.getUsername(),
                user.getPassword(),
                user.getFullName()
                );
    }

    @Override
    public void setAdmin(String username) throws SQLException, ClassNotFoundException {
        userRepository.setAdmin(username);
    }

    @Override
    public User findUser(String username) throws SQLException, ClassNotFoundException {
        return userRepository.findUser(username);
    }


}
