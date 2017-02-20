package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.UserGoal;
import project.persistence.repositories.GoalRepository;
import project.service.GoalService;

import java.util.Collections;
import java.util.List;

@Service
public class GoalServiceImplementation implements GoalService {

    // Instance Variables
    GoalRepository repository;

    // Dependency Injection
    @Autowired
    public GoalServiceImplementation(GoalRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserGoal save(UserGoal userGoal) {
        return repository.save(userGoal);
    }
    

    @Override
    public void delete(UserGoal userGoal) {
        repository.delete(userGoal);
    }

    @Override
    public List<UserGoal> findAll() {
        return repository.findAll();
    }

    @Override
    public List<UserGoal> findAllReverseOrder() {
        // Get all the userExercises
        List<UserGoal> userGoals = repository.findAll();

        // Reverse the list
        Collections.reverse(userGoals);

        return userGoals;
    }

    @Override
    public UserGoal findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<UserGoal> findByUserID(Long userID) {
        return repository.findByUserID(userID);
    }

    @Override
    public List<Object[]> findAllUserGoals(Long userID) { return repository.findAllUserGoals(userID); }

    @Override
    public List<Object[]> findOneUserGoal(Long userID, Long userGoalID) {return repository.findOneUserGoal(userID, userGoalID); }

}
