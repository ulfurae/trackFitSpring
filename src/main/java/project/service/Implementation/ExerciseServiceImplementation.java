package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Exercise;
import project.persistence.repositories.ExerciseRepository;
import project.service.ExerciseService;

import java.util.Collections;
import java.util.List;

@Service
public class ExerciseServiceImplementation implements ExerciseService {

    // Instance Variables
    ExerciseRepository repository;

    // Dependency Injection
    @Autowired
    public ExerciseServiceImplementation(ExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Exercise save(Exercise exercise) {
        return repository.save(exercise);
    }

    @Override
    public void delete(Exercise exercise) {
        repository.delete(exercise);
    }

    @Override
    public List<Exercise> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Exercise> findAllReverseOrder() {
        // Get all the exercises
        List<Exercise> exercises = repository.findAll();

        // Reverse the list
        Collections.reverse(exercises);

        return exercises;
    }

    @Override
    public Exercise findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Exercise> findByName(String name) {
        return repository.findByName(name);
    }


}
