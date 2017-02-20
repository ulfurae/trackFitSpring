package project.service;

import project.persistence.entities.UserGoal;

import java.util.List;

public interface GoalService {

    /**
     * Save a {@link UserGoal}
     * @param userGoal {@link UserGoal} to be saved
     * @return {@link UserGoal} that was saved
     */
    UserGoal save(UserGoal userGoal);

    /**
     * Delete {@link UserGoal}
     * @param userGoal {@link UserGoal} to be deleted
     */
    void delete(UserGoal userGoal);

    /**
     * Get all {@link UserGoal}s
     * @return A list of {@link UserGoal}s
     */
    List<UserGoal> findAll();

    /**
     * Get all {@link UserGoal}s in a reverse order
     * @return A reversed list of {@link UserGoal}s
     */
    List<UserGoal> findAllReverseOrder();

    /**
     * Find a {@link UserGoal} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link UserGoal} with {@link Long id}
     */
    UserGoal findOne(Long id);
    
    /**
     * Find all {@link UserGoal}s with {@link Integer userID}
     * @param userID {@link Integer}
     * @return All {@link UserGoal}s with the {@link Integer userID} passed
     */
    List<UserGoal> findByUserID(Long userID);

    List<Object[]> findAllUserGoals(Long userID);

    List<Object[]> findOneUserGoal(Long userID, Long userGoalID );


}
