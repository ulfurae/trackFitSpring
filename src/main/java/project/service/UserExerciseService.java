package project.service;

import project.persistence.entities.UserExercise;

import java.util.List;

public interface UserExerciseService {

    /**
     * Save a {@link UserExercise}
     * @param userExercise {@link UserExercise} to be saved
     * @return {@link UserExercise} that was saved
     */
    UserExercise save(UserExercise userExercise);

    /**
     * Delete {@link UserExercise}
     * @param userExercise {@link UserExercise} to be deleted
     */
    void delete(UserExercise userExercise);

    /**
     * Get all {@link UserExercise}s
     * @return A list of {@link UserExercise}s
     */
    List<UserExercise> findAll();

    /**
     * Get all {@link UserExercise}s in a reverse order
     * @return A reversed list of {@link UserExercise}s
     */
    List<UserExercise> findAllReverseOrder();

    /**
     * Find a {@link UserExercise} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link UserExercise} with {@link Long id}
     */
    UserExercise findOne(Long id);

    /**
     * Find all {@link UserExercise}s with {@link Integer userID}
     * @param userID {@link Integer}
     * @return All {@link UserExercise}s with the {@link Integer userID} passed
     */
    List<UserExercise> findByUserID(Long userID);

    List<Object[]> findAllUserExercises(Long userID);

}
