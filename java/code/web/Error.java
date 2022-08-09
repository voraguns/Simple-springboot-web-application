package web;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
class Error implements ErrorController  {
	
	@GetMapping("/error")
	String show(Model m) {
		m.addAttribute("title", "Not Found");
		m.addAttribute("detail", "Unable to find your request.");
		return "display";
	}
}