package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import project.service.Implementation.UserServiceImplementation;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){

        // get logged in user from global variable UserServiceImplementation.loggedInUser
        User loggedInUser = UserServiceImplementation.loggedInUser;

        // if loggedInUser is null then redirect to login page
        // else go to main page
       if(loggedInUser == null) {
           return "redirect:/login";
       } else {
           model.addAttribute("user",loggedInUser.getUsername());
           return "Index";
       }

    }


}
