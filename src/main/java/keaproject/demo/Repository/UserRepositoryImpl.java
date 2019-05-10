package keaproject.demo.Repository;

import keaproject.demo.Config.DBConfig;
import keaproject.demo.Model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.thymeleaf.exceptions.TemplateProcessingException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
    @Override
    public void insertUsertoDB(String username, String password, String fullName) throws SQLException, ClassNotFoundException {
        String sql_insertPatient = "INSERT INTO user" +
                "(username, " +
                "password, " +
                "fullName, "+
                "isAdmin) " +

                "VALUES " +
                "(?, ?, ?, '" + 0 +"')";
        PreparedStatement pstmt = null;

        try {
            Connection c = DBConfig.getConnection();
            pstmt = c.prepareStatement(sql_insertPatient);
            pstmt.setString(1, username);
            pstmt.setString(2,password);
            pstmt.setString(3, fullName);
            pstmt.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public User findUser(String username) throws SQLException, ClassNotFoundException, TemplateProcessingException {
        String FindPatientData = "SELECT * FROM user WHERE username = ?";
        User user = null;
        PreparedStatement pstmt = null;

        try {
            pstmt = DBConfig.getConnection().prepareStatement(FindPatientData);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getInt("user_id");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                boolean isAdmin = rs.getBoolean("isAdmin");

                user = new User(id, userName, password, fullName, isAdmin);
            }
            rs.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
        }
        return user;
    }

    @Override
    public void setAdmin(String username) throws SQLException, ClassNotFoundException {
        String updateAdmin = "UPDATE user " +
                "SET isAdmin = '" + 1 +
                "' WHERE (username = ?);";
        PreparedStatement pstmt = null;

        try {
            Connection c = DBConfig.getConnection();
            pstmt = c.prepareStatement(updateAdmin);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public <S extends User> S save(S s) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<User> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long aLong) {
        return null;
    }


    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Long> iterable) {
        return null;
    }


    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }


    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    private void SQLExecute(String SQL) throws ClassNotFoundException, SQLException{
        Statement stmt = DBConfig.getConnection().createStatement();
        stmt.execute(SQL);
        stmt.close();
    }
}
