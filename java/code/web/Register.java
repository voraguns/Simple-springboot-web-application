package web;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class Register {

    @RequestMapping("/register")
    String showRegisterPage(HttpSession session) {
        Member m = (Member) session.getAttribute("member");
        if (m == null) {
            return "register";
        } else {
            return "redirect:/content";
        }
    }

    @PostMapping("/register")
    String registerNewMember(
            String email,
            @RequestParam("first-name") String first,
            @RequestParam("last-name") String last,
            String password) {

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        
        
        boolean p0 = email.matches("^(.+)@(.+)$");
        boolean p1 = password.matches(pattern);
        boolean p2 = first.matches("^.{2,20}$");
        boolean p3 = last.matches("^.{2,20}$");
        
        boolean success = false;
        if (p0 && p1 && p2 && p3) {
            success = true;
        }
        
        if (success){
            EntityManager manager = Common.getManager();
            try {
                Team team = manager.find(Team.class, 1);
                Member m = new Member();
                m.email = email;
                m.firstName = first;
                m.lastName = last;
                m.password = Common.encrypt(password);
                m.team = team;
                
                manager.getTransaction().begin();
                manager.persist(m);
                manager.getTransaction().commit();
                
                Activate a = new Activate();
                a.member = m;
                a.secret = Common.random(20);
                
                manager.getTransaction().begin();
                manager.persist(m);
                manager.getTransaction().commit();
                
                Email e = new Email();
                e.sendActivationCode(m.email, a.secret, a.code);
            } catch (Exception e) {
                // in case of duplicate email
                success = false;
            } 
            manager.close();
        }
        
        if(success) {
            return "redirect:/member-register-success";
        }
        return "redirect:/member-register-error";
    }

    @RequestMapping("/member-register-success")
    String registerSuccess(Model m) {
        m.addAttribute("title", "Registration Successfully");
        m.addAttribute("detail", "Please go to your email box to active " + "your account.");
        return "register-status";
    }

    @RequestMapping("/member-register-error")
    String registerError(Model m) {
        m.addAttribute("title", "Registration Failed");
        m.addAttribute("detail", "Uable to register with your email.");
        return "register-status";
    }

}
