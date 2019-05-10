package keaproject.demo.Repository;

import keaproject.demo.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findLatestPost() throws SQLException, ClassNotFoundException;

    Post findPost(long id) throws SQLException, ClassNotFoundException;

    void insertPostToDB(String title, String author, String body) throws SQLException, ClassNotFoundException;

    void updatePost(long id, String title, String body) throws SQLException, ClassNotFoundException;

    void deletePost(long id) throws SQLException, ClassNotFoundException;
}
