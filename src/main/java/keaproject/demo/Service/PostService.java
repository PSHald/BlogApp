package keaproject.demo.Service;

import keaproject.demo.Model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostService {
    List<Post> newestToOldestPost() throws SQLException, ClassNotFoundException;

    Post findPost(long id) throws SQLException, ClassNotFoundException;

    void createPost(Post post) throws SQLException, ClassNotFoundException;

    void updatePost(long id, Post post) throws SQLException, ClassNotFoundException;

    public void deletePost(long id)throws SQLException, ClassNotFoundException;
}
