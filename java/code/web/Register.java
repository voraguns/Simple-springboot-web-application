package web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
class Register {
    
    @RequestMapping("/register")
    String showRegisterPage(HttpSession http) {       
        return "register";
    }
}
