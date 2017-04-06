package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.persistence.entities.Exercise;
import project.persistence.entities.User;
import project.persistence.entities.UserExercise;
import project.persistence.entities.UserGoal;
import project.service.ExerciseService;
import project.service.GoalService;
import project.service.Implementation.UserServiceImplementation;
import project.service.UserExerciseService;
import project.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class GoalController {

    // Instance Variables
    GoalService goalService;
    UserExerciseService uExerciseService;
    UserService userService;
    ExerciseService exerciseService;
    UserGoal currentUserGoal = null;

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
    // Dependency Injection
    @Autowired
    public GoalController(GoalService goalService, UserExerciseService uExerciseService, UserService userService, ExerciseService exerciseService) {
        this.goalService = goalService;
        this.uExerciseService = uExerciseService;
        this.userService = userService;
        this.exerciseService = exerciseService;
    }

    /**
     * Function receives data and makes a new UserGoal entry in database
     * @param userId is the name of the user adding this new entry
     * @param exercise is the name of the exercise
     * @param rep are the repetitions that the user did on this exercise
     * @param amount is the weight that the user lifted
     * @param startDate is the date user chooses to start working on that goal
     * @param endDate is the due date of the goal
     * @param status is a string that shows if goal is in process or finished
     * @return true if the UserGoal was successfully saved in database
     * @return false if the UserGoal was not successfully saved in database
     */
    @RequestMapping(value = "/addGoal")
    public String addGoalPost(@RequestParam Long userId, String exercise, String rep, String amount, String startDate, String endDate, String status) {
    	try {
    		//create new User entity and define it the current logged in user
			User user = userService.findById(userId);
		
			//create new UserGoal entity
			UserGoal uGoal = new UserGoal();
			
			//create new Exercise and define it by chosen exercise
			Exercise exerciseNew = exerciseService.findByName(exercise);
			
			//get input data from front end
			int repetitions = Integer.parseInt(rep);
			int amountKg = Integer.parseInt(amount);
			Date gstartDate = format.parse(startDate);
			Date gendDate = format.parse(endDate);
			Long exerciseID = exerciseNew.getId();
			
			//System.out.println(gstartDate);
			
			//assign input data to the parameters of UserGoal
			uGoal.setUserID(user.getId());
			uGoal.setStartDate(gstartDate);
			uGoal.setEndDate(gendDate);
            uGoal.setUnit1(repetitions);
            uGoal.setUnit2(amountKg);
            uGoal.setExerciseID(exerciseID);
			uGoal.setStatus(status);
			
			// Save the UserGoal to database
			goalService.save(uGoal);
			
			return "true";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "false";
    	}
    }
    
    /**
     * Function finds all UserGoal entries belonging to a certain user and returns them
     * @param userName is the name of the user who's trying to get all his UserGoal entries
     * @return List<UserGoal> if the user was found in database
     * @return null if user not found or if something went wrong
     */
	@RequestMapping("/goalLog")
    public List<UserGoal> userGoal(@RequestParam String userId){
		try {
			
			Long parsedUserId = Long.parseLong(userId);
			
			//Find User entity by his userName
			User user = userService.findById(parsedUserId);
			
			if(user != null) {
				//If User found, return a list of his UserGoals
				List<UserGoal> userGoal = goalService.findByUserID(parsedUserId);
		    	
		        return userGoal;
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
	
	@RequestMapping(value = "/deleteGoal")
    public String deleteGoal(@RequestParam String goalId){
    	
    	try {
    		//Change String exerciseId input to long
    		Long userGoalId = Long.parseLong(goalId);
    		
    		//Find userExercise with certain Id
    		UserGoal uGoal = goalService.findOne(userGoalId);
        	
    		//Delete the userExercise found
        	goalService.delete(uGoal);
        	
        	return "true";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "false";
    	}

    }
}
