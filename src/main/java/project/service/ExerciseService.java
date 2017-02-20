package project.service;

import project.persistence.entities.Exercise;

import java.util.List;

public interface ExerciseService {

    /**
     * Save a {@link Exercise}
     * @param exercise {@link Exercise} to be saved
     * @return {@link Exercise} that was saved
     */
    Exercise save(Exercise exercise);

    /**
     * Delete {@link Exercise}
     * @param exercise {@link Exercise} to be deleted
     */
    void delete(Exercise exercise);

    /**
     * Get all {@link Exercise}s
     * @return A list of {@link Exercise}s
     */
    List<Exercise> findAll();

    /**
     * Get all {@link Exercise}s in a reverse order
     * @return A reversed list of {@link Exercise}s
     */
    List<Exercise> findAllReverseOrder();

    /**
     * Find a {@link Exercise} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Exercise} with {@link Long id}
     */
    Exercise findOne(Long id);

    /**
     * Find all {@link Exercise}s with {@link String name}
     * @param name {@link String}
     * @return All {@link Exercise}s with the {@link String name} passed
     */
    List<Exercise> findByName(String name);

}
