package web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
class Login {
    
    @RequestMapping("/login")
    String showLogin(HttpSession session) {
        return "login";
    }
}
