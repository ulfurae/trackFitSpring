package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.UserExercise;
import project.persistence.repositories.UserExerciseRepository;
import project.service.UserExerciseService;

import java.util.Collections;
import java.util.List;

@Service
public class UserExerciseServiceImplementation implements UserExerciseService {

    // Instance Variables
    UserExerciseRepository repository;

    // Dependency Injection
    @Autowired
    public UserExerciseServiceImplementation(UserExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserExercise save(UserExercise userExercise) {
        return repository.save(userExercise);
    }

    @Override
    public void delete(UserExercise userExercise) {
        repository.delete(userExercise);
    }

    @Override
    public List<UserExercise> findAll() {
        return repository.findAll();
    }

    @Override
    public List<UserExercise> findAllReverseOrder() {
        // Get all the userExercises
        List<UserExercise> userExercises = repository.findAll();

        // Reverse the list
        Collections.reverse(userExercises);

        return userExercises;
    }

    @Override
    public UserExercise findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<UserExercise> findByUserID(Long userID) {
        return repository.findByUserID(userID);
    }

    @Override
    public List<Object[]> findAllUserExercises(Long userID) {return repository.findAllUserExercises(userID); }

}
