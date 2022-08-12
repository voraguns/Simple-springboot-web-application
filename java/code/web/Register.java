package web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
class Register {
    
    @RequestMapping("/register")
    String showRegisterPage(HttpSession http) {       
        return "register";
    }
    
    @PostMapping("/register")
    String registerNewMember() {
        return "finish";
    }
    
    @RequestMapping("/member-register-success")
    String registerSuccess(){
        return "register-status";
    }
    
    @RequestMapping("/member-register-error")
    String registerError(){
        return "register-status";
    }
    
}
