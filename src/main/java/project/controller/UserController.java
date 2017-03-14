package project.controller;

import java.util.Date;

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

    /**
     * Function searches for user with name input and returns the User
     * @param name is the userName used to find User
     * @return User
     */
    @RequestMapping("/profile")
    public User userProfile(@RequestParam String name) {
    		
    		//Find user by userName
    		User user = userService.findByUsername(name);
    		
    		if(user != null) {
    			//If user found, return User as JSON
    			return user;
    		}
    		else {
    			//If user not found, return an empty User as JSON
    			User notFoundUser = new User(null,null,null,null,0,0);
    			return notFoundUser;
    		}
    }
    
    /**
     * Function takes a new weight and saves it in database
     * @param weight is the new weight 
     * @param userName is the name of the user that wants to change his weight
     * @return true if the new weight was successfully saved in database
     * @return false if the new weight was not successfully saved in database
     */
    @RequestMapping(value = "/changeProfile")
    public boolean viewProfileChange(@RequestParam String weight, String userName){
    	
    	try { 
    		//Find user by his userName
    		User oldUser = userService.findByUsername(userName);
    		
    		//Change String weight input to integer
    		int newWeight = Integer.parseInt(weight);
    		
    		//Change the user's weight
        	oldUser.setWeight(newWeight);
        	
        	//Save changes in database
        	userService.save(oldUser);
        	
        	return true;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}

    }

}
