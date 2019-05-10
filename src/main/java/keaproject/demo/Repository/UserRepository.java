package keaproject.demo.Repository;

import keaproject.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void insertUsertoDB(String username, String password, String fullName) throws SQLException, ClassNotFoundException;

    User findUser(String id) throws SQLException, ClassNotFoundException;

    void setAdmin(String username) throws SQLException, ClassNotFoundException;
}
