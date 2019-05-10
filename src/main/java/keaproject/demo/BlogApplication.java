package keaproject.demo;

import keaproject.demo.Model.User;
import keaproject.demo.Repository.PostRepository;
import keaproject.demo.Repository.PostRepositoryImpl;
import keaproject.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class BlogApplication{

    public static void main(String[] args) throws NoClassDefFoundError {
        SpringApplication.run(BlogApplication.class, args);

    }
}
//Kommentar system, med sub beskeder, database tabel foreign key til Post_ID, samt hvis Kommentar_id hvis bundet til anden kommentar
//Tilpas bootswatch CSS - hvis tid til det