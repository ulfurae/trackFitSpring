package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.BMI;
import project.persistence.entities.User;
import project.service.FormulaService;
import project.service.Implementation.UserServiceImplementation;
import project.service.UserService;

@org.springframework.web.bind.annotation.RestController
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

    // Method that returns a User as JSON
    // uses name param to find correct user
    @RequestMapping("/profile")
    public User userProfile(@RequestParam String name) {

    	// get user from name
        User user = userService.findByUsername(name);

        // calculate BMI
        BMI BMI = formulaService.BMICalculate(user.getHeight(), user.getWeight());
        String newBirthday = formulaService.changeDateFormat(user.getBirthday());

        // Return the user as JSON
        return user;
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
