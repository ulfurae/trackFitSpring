package project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.persistence.entities.User;
import project.security.impl.AuthenticationManagerImpl;
import project.service.UserService;


@RestController
public class LoginController {

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
    // Dependency Injection to instance variable
    @Autowired
    private UserService userService;



	// Method that receives the POST request on the URL /login
	@RequestMapping(value = "/login")
	public User loginUser(@RequestParam(value = "username", required = true) String username,
							@RequestParam(value = "password", required = true) String password) {

		// create new AuthenticationManager object
		AuthenticationManager authenticationManager = new AuthenticationManagerImpl(userService);

		// check if authenticationManager authorizes username and password
		try {
			Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
			Authentication result = authenticationManager.authenticate(authRequest);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (AuthenticationException e) {
			//LOGGER.error("Authentication failed: {}", e);
			System.out.print("Authentication failed: {}" + e);
			return null;
		}

		System.out.print("fwefewf" + userService.getLoggedInUser());
		//LOGGER.debug("Successfully authenticated. Security context contains: {}",
		//		SecurityContextHolder.getContext().getAuthentication());

		// update userService.loggedInUser with new user that logged in
		User loggedInUser = userService.getLoggedInUser();

		return loggedInUser;
	}


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
    public String registerUser(@RequestParam String username, String password, String fullName, String birthday, String height, String weight) {
    	try {
    		//create new User entity
			User user = new User();
			
			Date bday = format.parse(birthday);
			int haed = Integer.parseInt(height);
			int þyngd = Integer.parseInt(weight);
			//int BMI = Integer.parseInt(bmi);

			
			//assign input data to the parameters of User
			user.setUsername(username);
			user.setPass(password);
			user.setFullName(fullName);
			user.setBirthday(bday);
			user.setHeight(haed);
			user.setWeight(þyngd);

			
			// Save the UserGoal to database
			userService.save(user);
			
			return "true";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "false";
    	}
    }


}
