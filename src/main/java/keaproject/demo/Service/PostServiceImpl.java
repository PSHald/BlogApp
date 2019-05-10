package keaproject.demo.Service;

import keaproject.demo.Model.Post;
import keaproject.demo.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    PostRepository postRepository;

    public PostServiceImpl(){

    }

    @Override
    public List<Post> newestToOldestPost() throws SQLException, ClassNotFoundException {
        return postRepository.findLatestPost();
    }

    @Override
    public Post findPost(long id) throws SQLException, ClassNotFoundException {
        return postRepository.findPost(id);
    }

    @Override
    public void createPost(Post post) throws SQLException, ClassNotFoundException {
        postRepository.insertPostToDB(post.getTitle(),
                post.getAuthor(),
                post.getBody()
        );
    }

    @Override
    public void updatePost(long id, Post post) throws SQLException, ClassNotFoundException {
        postRepository.updatePost(id, post.getTitle(), post.getBody());
    }

    public void deletePost(long id)throws SQLException, ClassNotFoundException{
        postRepository.deletePost(id);
    }
}
