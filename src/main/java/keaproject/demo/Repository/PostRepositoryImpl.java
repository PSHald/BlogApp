package keaproject.demo.Repository;

import keaproject.demo.Config.DBConfig;
import keaproject.demo.Model.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.thymeleaf.exceptions.TemplateProcessingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("PostRepository")
public class PostRepositoryImpl implements PostRepository {

    public void createPostTable() throws ClassNotFoundException, SQLException {
        String sql_createDiagnoseTable = "CREATE TABLE IF NOT EXISTS " + "pshdb" + ".post" +
                "(post_id int(8) NOT NULL AUTO_INCREMENT,"  +
                "title varchar(100) NOT NULL," +
                "author varchar(100) NOT NULL, " +
                "body varchar(5000) NOT NULL, " +
                "FOREIGN KEY(author)" +
                "REFERENCES user(username)" +
                "ON UPDATE CASCADE ON DELETE CASCADE" +
                "ADD PRIMARY KEY('post_id)'" +
                ")";

        SQLExecute(sql_createDiagnoseTable);
    }

    @Override
    public List<Post> findLatestPost() throws SQLException, ClassNotFoundException {
        Statement stmt = DBConfig.getConnection().createStatement();
        String FindKonsultationData = "SELECT * FROM post ORDER BY post_id DESC;";
        List<Post> posts = new ArrayList<>();

        ResultSet rs = stmt.executeQuery(FindKonsultationData);
        while(rs.next()){
            long Id = rs.getInt("post_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String body = rs.getString("body");
            posts.add(new Post(Id, title, author, body));
        }
        stmt.close();
        return posts;
    }

    @Override
    public Post findPost(long id) throws SQLException, ClassNotFoundException, TemplateProcessingException {
        Post post = null;
        String FindPatientData = "SELECT * FROM post WHERE (post_id = ?);";
        PreparedStatement pstmt = null;

        try {
            pstmt = DBConfig.getConnection().prepareStatement(FindPatientData);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long Id = rs.getLong("post_id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String author = rs.getString("author");

                post = new Post(Id, title,  author, body);
            }
            rs.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
        }
        return post;
    }

    @Override
    public void insertPostToDB(String title, String author, String body) throws SQLException, ClassNotFoundException {
        String sql_insertPost = "INSERT INTO post" +
                "(title, " +
                "author, " +
                "body) " +

                "VALUES " +
                "(?, ?, ?)";
        /*"VALUES " +
                "('" + title + "', '" +
                author + "', '" +
                body  +"')";
                */
        PreparedStatement pstmt = null;

        try {
            Connection c = DBConfig.getConnection();
            pstmt = c.prepareStatement(sql_insertPost);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, body);
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
    public void updatePost(long id, String title, String body) throws SQLException, ClassNotFoundException {
        String sql_update = "UPDATE post " +
                "SET title = ?" +
                ", body = ?" +
                " WHERE (post_id = ?);";
        PreparedStatement pstmt = null;

        try {
            Connection c = DBConfig.getConnection();
            pstmt = c.prepareStatement(sql_update);
            pstmt.setString(1, title);
            pstmt.setString(2, body);
            pstmt.setLong(3, id);
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
    public void deletePost(long id) throws SQLException, ClassNotFoundException {
        String deletePost = "DELETE FROM post WHERE (post_id = ?)";
        PreparedStatement pstmt = null;

        try {
            Connection c = DBConfig.getConnection();
            pstmt = c.prepareStatement(deletePost);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
        }
    }


    private void SQLExecute(String SQL) throws ClassNotFoundException, SQLException{
        Statement stmt = DBConfig.getConnection().createStatement();
        stmt.execute(SQL);
        stmt.close();
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public List<Post> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Post> findAllById(Iterable<Long> iterable) {
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
    public void delete(Post post) {

    }

    @Override
    public void deleteAll(Iterable<? extends Post> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Post> S save(S s) {
        return null;
    }

    @Override
    public <S extends Post> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Post> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Post> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Post getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Post> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Post> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Post> boolean exists(Example<S> example) {
        return false;
    }
}
