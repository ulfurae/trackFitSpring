package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Exercise;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise save(Exercise exercise);

    void delete(Exercise exercise);

    List<Exercise> findAll();

    List<Exercise> findAllByOrderByIdDesc();

    Exercise findOne(Long id);

    List<Exercise> findByName(String name);
}
