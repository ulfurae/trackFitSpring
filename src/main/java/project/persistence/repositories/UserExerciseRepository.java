package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.UserExercise;

import java.util.List;

public interface UserExerciseRepository extends JpaRepository<UserExercise, Long> {

    UserExercise save(UserExercise userExercise);

    void delete(UserExercise userExercise);

    UserExercise findOne(Long id);

    List<UserExercise> findByUserID(Long userID);

    // [0] userExerciseID , [1] name , [2] unit1 , [3] unit2 ,
    // [4] date , [5] exerciseID , [6] type , [7] goalID
    @Query(value =  "SELECT ue.id, e.name, ue.unit1, ue.unit2, "     +
                    "to_char(ue.date, 'dd Mon YYYY'), e.id, e.type, ue.userGoalID " +
                    "FROM Exercise e, UserExercise ue, User u "      +
                    "WHERE e.id = ue.exerciseID AND ue.userID = u.id AND u.id = ? " +
                    "order by ue.date desc")
    List<Object[]> findAllUserExercises(Long id);
}
