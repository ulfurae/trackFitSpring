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

    
    /**
     * Function searches for all possible exercises that the user can register
     * @return list of exercises
     */
    @RequestMapping(value = "/getExercises")
    public List<Exercise> addExerciseGet(){
    	
    	//Find all exercises in database
    	List<Exercise> exercises = exerciseService.findAll();

        return exercises;
    }
    
    /**
     * 
     * @param exerciseId
     * @return
     */
    @RequestMapping(value = "/deleteExercise")
    public String deleteExercise(@RequestParam String exerciseId){
    	
    	try {
    		//Change String exerciseId input to long
    		Long userExerciseId = Long.parseLong(exerciseId);
    		
    		//Find userExercise with certain Id
    		UserExercise uExercise = uExerciseService.findOne(userExerciseId);
        	
    		//Delete the userExercise found
        	uExerciseService.delete(uExercise);
        	
        	return "true";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "false";
    	}

    }

    /**
     * Function receives data and makes a new UserExercise entry in database
     * @param userId is the name of the user adding this new entry
     * @param goalID is the id of a goal that the exercise entry belongs to
     * @param exercise is the name of the exercise
     * @param rep are the repetitions that the user did on this exercise
     * @param amount is the weight that the user lifted
     * @return true if the UserExercise was successfully saved in database
     * @return false if the UserExercise was not successfully saved in database
     */
    @RequestMapping(value = "/addExercise")
    public String addExercisePost(@RequestParam Long userId, String goalID, String exercise, String rep, String amount) {

    	try {
    		//Find User entity by his userName
        	User user = userService.findById(userId);
        	
        	//Make new UserExercise
        	UserExercise userExercise = new UserExercise();
        	
        	//Find Exercise entity with the name of the exercise
        	Exercise exerciseFound = exerciseService.findByName(exercise);
        	
        	//Change String goalID input to Long
        	Long goalIDLong = Long.parseLong(goalID);
        	//Change String rep input to integer
        	int repetitions = Integer.parseInt(rep);
        	//Change String amount input to integer
        	int amountKg = Integer.parseInt(amount);
        	
        	//Get the exerciseId from the Exercise entity
        	Long exerciseID = exerciseFound.getId();

        	//Set the variables of the UserExercise
            userExercise.setDate(new Date());
            userExercise.setUserID(user.getId());
            userExercise.setUserGoalID(goalIDLong);
            userExercise.setUnit1(repetitions);
            userExercise.setUnit2(amountKg);
            userExercise.setExerciseID(exerciseID);

            // Save the UserExercise received by this function in database
            uExerciseService.save(userExercise);
            
            return "true";
    	} catch(Exception e){
    		return "false";
    	}
    }
    
    /**
     * Function finds all UserExercise entries belonging to a certain user and returns them
     * @param userId is the name of the user who's trying to get all his UserExercise entries
     * @return List<UserExercise> if the user was found in database
     * @return null if user not found or if something went wrong
     */
	@RequestMapping("/exerciseLog")
    public List<UserExercise> userExercise(@RequestParam Long userId){
		try {
			//Find User entity by his userName
			User user = userService.findById(userId);
			
			if(user != null) {
				//If User found, return a list of his UserExercises
				List<UserExercise> userExercise = uExerciseService.findByUserID(user.getId());
		    	
		        return userExercise;
    		}
    		else {
    			//If User not found, return null
    			return null;
    		}
	    	
		} catch(Exception e) {
			e.printStackTrace();
			return null; 
		}
    }
}
