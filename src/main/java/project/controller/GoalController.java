package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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


@Controller
public class GoalController {

    // Instance Variables
    GoalService goalService;
    UserExerciseService uExerciseService;
    UserService userService;
    ExerciseService exerciseService;
    UserGoal currentUserGoal = null;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    // Dependency Injection
    @Autowired
    public GoalController(GoalService goalService, UserExerciseService uExerciseService) {
        this.goalService = goalService;
        this.uExerciseService = uExerciseService;
    }

    // GET method that returns the correct view for the URL /addGoal
    @RequestMapping(value = "/addGoal", method = RequestMethod.GET)
    public String addGoalGet(Model model){

        // connecting the UserGoal object to the form
        model.addAttribute("goalForm",new UserGoal());

        return "GoalAdd";
    }

    // Method that receives the POST request on the URL /addGoal and receives the ModelAttribute("addGoal")
    @RequestMapping(value = "/addGoal")
    public String addGoalPost(@RequestParam String userName, String exercise, String rep, String amount, String startDate, String endDate, String status) {
    	try {
			// get logged in user from global variable UserServiceImplementation.loggedInUser
			//User user = UserServiceImplementation.loggedInUser;
			User user = userService.findByUsername(userName);
			UserGoal uGoal = new UserGoal();
			Exercise exerciseNew = exerciseService.findByName(exercise);
			
			int repetitions = Integer.parseInt(rep);
			int amountKg = Integer.parseInt(amount);
			Date gstartDate = format.parse(startDate);
			Date gendDate = format.parse(endDate);
			Long exerciseID = exerciseNew.getId();
			
			uGoal.setUserID(user.getId());
			uGoal.setStartDate(gstartDate);
			uGoal.setEndDate(gendDate);
            uGoal.setUnit1(repetitions);
            uGoal.setUnit2(amountKg);
            uGoal.setExerciseID(exerciseID);
			uGoal.setStatus("not completed");
			
			// Save the UserGoal that is received from the form
			goalService.save(uGoal);
			
			return "true";
    	} catch(Exception e){
    		return "false";
    	}
    }
    
 // GET method that returns the view for the URL /viewPerformace
    @RequestMapping(value = "/goalLog", method = RequestMethod.GET)
    public String viewGoalLogGet(Model model){

        // get logged in user from global variable UserServiceImplementation.loggedInUser
        User user = UserServiceImplementation.loggedInUser;

        // Here we get all the UserExercises by userID and add them to the model
        model.addAttribute("goals", goalService.findAllUserGoals(user.getId()));

        // Return the view
        return "GoalLog";
    }

    // Method that returns the correct view for the URL /goal/{goalID}
    // This method finds and returns the UserGoal with the requested {goalID}
    @RequestMapping(value = "/goal/{id}", method = RequestMethod.GET)
    public String userGoalGet(@PathVariable Long id,
                                             Model model){

        // set current userGoal
        currentUserGoal  = goalService.findOne(id);

        // Get UserGoal with this id and add it to the model
        model.addAttribute("goal", goalService.findOneUserGoal(currentUserGoal.getUserID(), id).get(0));
        model.addAttribute("exerciseForm", new UserExercise());


        // Return the view
        return "UserGoal";
    }

    @RequestMapping(value = "/addExerciseGoal", method = RequestMethod.POST)
    public String addExerciseGoalPost(@ModelAttribute("addExerciseGoal") UserExercise uExercise, Model model) {

        // set mock values into UserExercise for testing
        uExercise.setDate(new Date());
        uExercise.setUserID(currentUserGoal.getUserID());
        uExercise.setUserGoalID(currentUserGoal.getId());
        uExercise.setExerciseID(currentUserGoal.getExerciseID());

        // Save the UserExercise that is received from the form

        uExerciseService.save(uExercise);

        // Refresh the form with a new UserExercise
        model.addAttribute("exerciseForm", new UserExercise());

        // Return the view
        return "redirect:/goal/" + currentUserGoal.getId();
    }
    
}
