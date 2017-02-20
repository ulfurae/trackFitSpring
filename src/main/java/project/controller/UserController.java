package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.persistence.entities.User;
import project.persistence.entities.BMI;
import project.service.Implementation.UserServiceImplementation;
import project.service.UserService;
import project.service.FormulaService;

@Controller
public class UserController {

    // Instance Variables
    UserService userService;
    FormulaService formulaService;

    // Dependency Injection
    @Autowired
    public UserController(UserService userService, FormulaService formulaService) {
        this.userService = userService;
        this.formulaService = formulaService;
    }

    // Method that returns the view for the URL /viewProfile
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String viewProfileGet(Model model){

    	// get logged in user
        User user = UserServiceImplementation.loggedInUser;

        // connect object User to the form
        model.addAttribute("user", user);
        
        // calculate BMI
        BMI BMI = formulaService.BMICalculate(user.getHeight(), user.getWeight());
        
        // connect object BMI to the form
        model.addAttribute("bmi", BMI);
        
        String newBirthday = formulaService.changeDateFormat(user.getBirthday());
    	
    	model.addAttribute("newBirthday", newBirthday);

        // Return the view
        return "Profile";
    }
    
    // Method that receives the POST request on the URL /viewProfile
    @RequestMapping(value = "/viewProfile", method = RequestMethod.POST)
    public String viewProfilePost(@ModelAttribute("newUser") User newUser,
                                     Model model){
    	
    	// mock Object User updating weight information
    	User oldUser = UserServiceImplementation.loggedInUser;
    	int weight = newUser.getWeight();
    	oldUser.setWeight(weight);
    	userService.save(oldUser);
    	
    	// mock Object User BMI changes if weight is updated
    	User updatedUser = UserServiceImplementation.loggedInUser;
    	// connect User object to the form
    	model.addAttribute("user", updatedUser);
    	// update BMI
    	BMI BMI = formulaService.BMICalculate(updatedUser.getHeight(), updatedUser.getWeight());
	   	// connect BMI object to the form
    	model.addAttribute("bmi", BMI);
    	
    	String newBirthday = formulaService.changeDateFormat(updatedUser.getBirthday());
    	
    	model.addAttribute("newBirthday", newBirthday);

        // Return the view
        return "Profile";
    }
    
    // Method that returns the view for the URL /viewProfile/change to update User information
    @RequestMapping(value = "/viewProfile/change", method = RequestMethod.GET)
    public String viewProfileChange(Model model){
    	
    	// connect User object to the form
    	model.addAttribute("newUser", new User());

        // Return the view
        return "ProfileChange";
    }

}
