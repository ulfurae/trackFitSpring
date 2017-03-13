package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.persistence.entities.Exercise;
import project.persistence.entities.User;
import project.persistence.entities.UserExercise;
import project.service.ExerciseService;
import project.service.Implementation.UserServiceImplementation;
import project.service.UserExerciseService;
import project.service.UserService;

import java.util.Date;
import java.util.List;

@RestController
public class ExerciseController {

    // Instance Variables
    ExerciseService exerciseService;
    UserExerciseService uExerciseService;
    UserService userService;

    // Dependency Injection
    @Autowired
    public ExerciseController(ExerciseService exerciseService, UserExerciseService uExerciseService, UserService userService) {
        this.exerciseService = exerciseService;
        this.uExerciseService = uExerciseService;
        this.userService = userService;
    }

    // GET method that returns the correct view for the URL /addExercise
    @RequestMapping(value = "/getExercises")
    public List<Exercise> addExerciseGet(){
    	
    	List<Exercise> exercises = exerciseService.findAll();
    	
    	System.out.println(exercises);

        return exercises;
    }

    @RequestMapping(value = "/addExercise")
    public String addExercisePost(@RequestParam String userName, String goalID, String exercise, String rep, String amount) {

    	try {
    		// get logged in user from global variable UserServiceImplementation.loggedInUser
    		//User user = UserServiceImplementation.loggedInUser;
        	User user = userService.findByUsername(userName);
        	UserExercise uExercise = new UserExercise();
        	Exercise exerciseNew = exerciseService.findByName(exercise);
        	
        	Long goalIDLong = Long.parseLong(goalID);
        	int repetitions = Integer.parseInt(rep);
        	int amountKg = Integer.parseInt(amount);
        	Long exerciseID = exerciseNew.getId();

            uExercise.setDate(new Date());
            uExercise.setUserID(user.getId());
            uExercise.setUserGoalID(goalIDLong);
            uExercise.setUnit1(repetitions);
            uExercise.setUnit2(amountKg);
            uExercise.setExerciseID(exerciseID);

            // Save the UserExercise that is received from the form
            uExerciseService.save(uExercise);
            
            return "true";
    	} catch(Exception e){
    		return "false";
    	}
    }
    
 // GET method that returns the view for the URL /viewPerformace
	@RequestMapping("/exerciseLog")
    public List<UserExercise> userExercise(@RequestParam String userName){
		try {
			User user = userService.findByUsername(userName);
	    	
	    	List<UserExercise> userExercise = uExerciseService.findByUserID(user.getId());
	    	
	        return userExercise;
		} catch(Exception e) {
			e.printStackTrace();
			return null; 
		}
    }
}
