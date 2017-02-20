package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.persistence.entities.User;
import project.security.impl.AuthenticationManagerImpl;
import project.service.Implementation.UserServiceImplementation;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    // Dependency Injection to instance variable
    @Autowired
    private UserService userService;

    // Logger object that handles error handling and debugging for log in and sign up
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    // Method that returns the view for the URL /login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login");

        // connect User object to the sign up form
        model.addAttribute("regForm", new User());

        return modelAndView;
    }

    // Method that receives the POST request on the URL /login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password) {

        // create new AuthenticationManager object
        AuthenticationManager authenticationManager = new AuthenticationManagerImpl(userService);

        // check if authenticationManager authorizes username and password
        try {
            Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            LOGGER.error("Authentication failed: {}", e);
            return "redirect:/login-failed";
        }

        LOGGER.debug("Successfully authenticated. Security context contains: {}",
                SecurityContextHolder.getContext().getAuthentication());

        // update userService.loggedInUser with new user that logged in
        userService.getLoggedInUser();

        return "redirect:/";
    }

    // Method that returns the view for the URL /login-failed
    @RequestMapping(value = "/login-failed", method = RequestMethod.GET)
    public ModelAndView loginFailedPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();

        // update model with error message
        modelAndView.addObject("loginErrorMessage", "Login failed, please try again.");
        modelAndView.setViewName("Login");
        // connect User object to the sign up form
        model.addAttribute("regForm", new User());

        return modelAndView;
    }

    // Method that receives the POST request on the URL /process-register
    @RequestMapping(value = "/process-register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, HttpServletRequest request,
                               HttpServletResponse response) {

        userService.save(user);

        if (user.getId() != null) { // registration is successful
            autoLoginUser(user);
        } else {
            LOGGER.warn("Registration failed for user {}", user.getUsername());
        }

        return "redirect:/";
    }

    // method that automatically logs in user after he has signed up
    private void autoLoginUser(User user) {

        // check if authenticationManager authorizes username and password
        try {
            AuthenticationManager authenticationManager = new AuthenticationManagerImpl(userService);
            Authentication authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPass());
            Authentication result = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (Exception e) {
            LOGGER.error("Couldn't login the user {} automatically: ", user.getUsername(), e);
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        // update userService.loggedInUser with new user that logged in
        userService.getLoggedInUser();

    }

    // Method that returns the view for the URL /loginout
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        // set global variable UserServiceImplementation.loggedInUser to null
        UserServiceImplementation.loggedInUser = null;
        // set SecurityContextHolder to null
        SecurityContextHolder.getContext().setAuthentication(null);

        return "redirect:/";
    }


}
