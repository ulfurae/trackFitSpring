package project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.persistence.entities.User;
import project.service.UserService;

@RestController
public class LoginController {

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
    // Dependency Injection to instance variable
    @Autowired
    private UserService userService;

    /**
     * Function receives data and makes a new UserGoal entry in database
     * @param userName is the username 
     * @param password is the password
     * @param fullname are the full name of user
     * @param birthday is the birthday of user
     * @param height is the height of user
     * @param weight is the current weight of user
     * @return true if the User was successfully saved in database
     * @return false if the User was not successfully saved in database
     */
    @RequestMapping(value = "/registerUser")
    public String registerUser(@RequestParam String userName, String password, String fullname, String birthday, String height, String weight, String bmi) {
    	try {
    		//create new User entity
			User user = new User();
			
			Date bday = format.parse(birthday);
			int haed = Integer.parseInt(height);
			int þyngd = Integer.parseInt(weight);
			//int BMI = Integer.parseInt(bmi);
			int BMI = 23; //placeholder
			
			//assign input data to the parameters of User
			user.setUsername(userName);
			user.setPass(password);
			user.setFullName(fullname);
			user.setBirthday(bday);
			user.setHeight(haed);
			user.setWeight(þyngd);
			user.setBMI(BMI);
			
			// Save the UserGoal to database
			userService.save(user);
			
			return "true";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "false";
    	}
    }


}
