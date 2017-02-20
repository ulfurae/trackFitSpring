package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import project.persistence.entities.UserExercise;
import project.service.ExerciseService;
import project.service.Implementation.UserServiceImplementation;
import project.service.UserExerciseService;

import java.util.Date;
import java.util.List;

@Controller
public class ExerciseController {

    // Instance Variables
    ExerciseService exerciseService;
    UserExerciseService uExerciseService;

    // Dependency Injection
    @Autowired
    public ExerciseController(ExerciseService exerciseService, UserExerciseService uExerciseService) {
        this.exerciseService = exerciseService;
        this.uExerciseService = uExerciseService;
    }

    // GET method that returns the correct view for the URL /addExercise
    @RequestMapping(value = "/addExercise", method = RequestMethod.GET)
    public String addExerciseGet(Model model){

        /* TODO: make this functional
        List el =  exerciseService.findAllReverseOrder();
        List el2 = new ArrayList();
        for(Object x : el) {
            Exercise b = (Exercise) x;
            el2.add(b.getName());
        }
        model.addAttribute("exercises", el2);
        */

        // connecting the UserExercise object to the form
        model.addAttribute("exerciseForm",new UserExercise());

        return "ExerciseAdd";
    }

    // Method that receives the POST request on the URL /addExercise and receives the ModelAttribute("addExercise")
    @RequestMapping(value = "/addExercise", method = RequestMethod.POST)
    public String addExercisePost(@ModelAttribute("addExercise") UserExercise uExercise, Model model) {

        // get logged in user from global variable UserServiceImplementation.loggedInUser
        User user = UserServiceImplementation.loggedInUser;

        // set mock values into UserExercise for testing
        uExercise.setDate(new Date());
        uExercise.setUserID(user.getId());
        uExercise.setUserGoalID((long) 0);

        // Save the UserExercise that is received from the form
        uExerciseService.save(uExercise);

        // Refresh the form with a new UserExercise
        model.addAttribute("exerciseForm", new UserExercise());

        // Return the view
        return "ExerciseAdd";
    }
    
 // GET method that returns the view for the URL /viewPerformace
    @RequestMapping(value = "/exerciseLog", method = RequestMethod.GET)
    public String viewExerciseLogGet(Model model){

        // get logged in user from global variable UserServiceImplementation.loggedInUser
        User user = UserServiceImplementation.loggedInUser;

        List<Object[]> list = uExerciseService.findAllUserExercises(user.getId());

        // Here we get all the UserExercises (in a reverse order) and add them to the model
        model.addAttribute("exercises", list);

        // Return the view
        return "ExerciseLog";
    }
    
  
}
