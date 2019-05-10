package keaproject.demo.Controller;

import keaproject.demo.Model.User;
import keaproject.demo.Service.PostServiceImpl;
import keaproject.demo.Service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("user")
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PostServiceImpl postService;

    private final String INDEX = "index";

    @GetMapping("/")
    public String home(User user){
        return INDEX;
    }

    @GetMapping("/register")
    public String register(Model model){
         try {
             model.addAttribute("user", new User());
             return "register";
         } catch (Exception ee) {
             System.out.println(ee.getMessage());
         }
         return INDEX;
    }

    @GetMapping("/login")
    public String login(HttpSession session, ModelMap model){
        try {
            session.removeAttribute("logged_in");
            model.remove("user");
            return "login";
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }
        return "login";
    }
}
