package keaproject.demo.Controller;

import keaproject.demo.Model.Post;
import keaproject.demo.Model.User;

import keaproject.demo.Service.PostServiceImpl;

import keaproject.demo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PostServiceImpl postService;

    @PostMapping("/register")
    public String register(User user, HttpSession session, Model model)throws SQLException, ClassNotFoundException{
        if(user == null){
            userService.createUser(user);
            User login = userService.login(user.getUsername(), user.getPassword());
            model.addAttribute("user", login);
            session.setAttribute("logged_in", RequestContextHolder.currentRequestAttributes().getSessionId());

        }
        return "blog";
    }

    @PostMapping("/login")
    public String login(HttpSession session, User user, Model model) throws SQLException, ClassNotFoundException, NullPointerException {
        User login = userService.login(user.getUsername(), user.getPassword());
        if(login != null){
            session.setAttribute("logged_in", RequestContextHolder.currentRequestAttributes().getSessionId());
            model.addAttribute("user", login);
            if (login.getIsAdmin() == true){
                return "adminSide";
            }
            else  if (login.getIsAdmin() == false){
                return "blog";
            }
        }
        model.addAttribute("error" , true);
        return "index";
    }


    @GetMapping("/userSide/{username}")
    public String userSide(@PathVariable String username, Model model, HttpSession session){
        try {
            Object v = session.getAttribute("logged_in");
            User user = userService.findUser(username);
            String c  = RequestContextHolder.currentRequestAttributes().getSessionId();
            if(v.equals(c) && user != null) {
                model.addAttribute("userData", user);
                return "userSide";
            } else {
                return "noaccess";
            }
        } catch (Exception ee) {
            return "noaccess";
        }
    }

    @GetMapping("/adminSide")
    public String adminSide(HttpSession session, User user){
        try {
            Object v = session.getAttribute("logged_in");
            String c  = RequestContextHolder.currentRequestAttributes().getSessionId();
            if(v.equals(c) && (user.getIsAdmin() == true)) {
                return "adminSide";
            } else {
                return "noaccess";
            }
        } catch (Exception ee) {
            return "noaccess";
        }
    }

    @GetMapping("/blog")
    public String blog(@ModelAttribute Post post, HttpSession session, Model model){
        try {

            model.addAttribute("posts", postService.newestToOldestPost());
            Object v = session.getAttribute("logged_in");
            String c  = RequestContextHolder.currentRequestAttributes().getSessionId();
            if(v.equals(c)) {
                return "blog";
            } else {
                return "login";
            }
        } catch (Exception ee) {
            return "noaccess";
        }
    }


    @GetMapping("/createPost")
    public String createPost(HttpSession session, User user, Model model){
        try {
            Object v = session.getAttribute("logged_in");
            String c  = RequestContextHolder.currentRequestAttributes().getSessionId();
            if(v.equals(c) && (user.getIsAdmin() == true)) {
                model.addAttribute("post", new Post());
                return "createPost";
            } else {
                return "noaccess";
            }
        } catch (Exception ee) {
            return "noaccess";
        }
    }

    @PostMapping("/createPost")
    public String createPost(User user, Post post) throws SQLException, ClassNotFoundException{
        post.setAuthor(user.getUsername());
        postService.createPost(post);
        return "adminSide";
    }

    @GetMapping("/setAdmin")
    public String setAdmin(HttpSession session, User user) {
        try {
            Object v = session.getAttribute("logged_in");
            String c  = RequestContextHolder.currentRequestAttributes().getSessionId();
            if(v.equals(c)  && (user.getIsAdmin() == true)) {
                return "setAdmin";
            } else {
                return "noaccess";
            }
        } catch (Exception ee) {
            return "noaccess";
        }
    }

    @PostMapping("/setAdmin")
    public String setAdmin(User user)throws ClassNotFoundException, SQLException{
        userService.setAdmin(user.getUsername());
        return "adminSide";
    }

    @GetMapping("/blog/post/{id}")
    public String post(@PathVariable long id, HttpSession session, Model model){
        try {
            Object v = session.getAttribute("logged_in");
            Post post = postService.findPost(id);
            String c  = RequestContextHolder.currentRequestAttributes().getSessionId();
            if(v.equals(c) && post != null ){
                model.addAttribute("post", post);
                return "post";
            }
            else{
                return "noaccess";
            }
        } catch (Exception ee) {
            return "noaccess";
        }
    }

    @GetMapping("/blog/post/{id}/deletePost")
    public String deltePost(@PathVariable long id) throws ClassNotFoundException, SQLException{
        postService.deletePost(id);
        return "redirect:/blog";
    }

    @GetMapping("/blog/post/{id}/updatePost")
    public String updatePost(@PathVariable long id, HttpSession session, User user, Model model){
        try {
            Object v = session.getAttribute("logged_in");
            String c  = RequestContextHolder.currentRequestAttributes().getSessionId();
            if(v.equals(c) && (user.getIsAdmin() == true)){
                Post post = postService.findPost(id);
                model.addAttribute("post", post);
                return "updatePost";
            } else {
                return "noaccess";
            }
        } catch (Exception ee) {
            return "noaccess";
        }
    }

    @PostMapping("/blog/post/{id}/updatePost")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) throws ClassNotFoundException, SQLException{
        postService.updatePost(id, post);
        return "adminSide";
    }

}
