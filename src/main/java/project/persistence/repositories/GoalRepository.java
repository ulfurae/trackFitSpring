package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.UserGoal;

import java.util.List;

public interface GoalRepository extends JpaRepository<UserGoal, Long> {

    UserGoal save(UserGoal userGoal);

    void delete(UserGoal userGoal);
    
    UserGoal findOne(Long id);

    List<UserGoal> findByUserID(Long userID);


    // [0] userGoalID , [1] name , [2] unit1 , [3] unit2 ,
    // [4] startDate , [5] endDate , [6] exerciseID , [7] type , [8] status
    @Query(value =  "SELECT ue.id, e.name, ue.unit1, ue.unit2, "  +
            "to_char(ue.startDate, 'dd Mon YYYY'), to_char(ue.endDate, 'dd Mon YYYY')," +
            "e.id, e.type, ue.status " +
            "FROM Exercise e, UserGoal ue, User u "   +
            "WHERE e.id = ue.exerciseID AND ue.userID = u.id AND u.id = ?"  +
            "order by ue.startDate desc" )
    List<Object[]> findAllUserGoals(Long userID);

    // [0] userGoalID , [1] name , [2] unit1 , [3] unit2 ,
    // [4] startDate , [5] endDate , [6] exerciseID , [7] type , [8] status
    @Query(value =  "SELECT ue.id, e.name, ue.unit1, ue.unit2, "  +
            "to_char(ue.startDate, 'dd Mon YYYY'), to_char(ue.endDate, 'dd Mon YYYY')," +
            "e.id, e.type, ue.status " +
            "FROM Exercise e, UserGoal ue, User u "   +
            "WHERE e.id = ue.exerciseID AND ue.userID = u.id AND u.id = ? AND ue.id = ?" )
    List<Object[]> findOneUserGoal(Long userID , Long userGoalID);

}
