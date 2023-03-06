package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    //@Query(name = User.DELETE)
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user = :userId")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Meal m SET m.dateTime = :datetime, m.calories= :calories," +
            "m.description=:desc where m.id=:id and m.user.id=:userId")
    Meal save(Meal meal, int userId);

    @Query("SELECT m FROM Meal m WHERE m.id = :id AND m.user.id=:userId")
    Meal get(@Param("id") int id, @Param("user_id") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(@Param("user_id") int userId);

    @Query(name = Meal.GET_BETWEEN)
    List<Meal> getBetweenHalfOpen(@Param("startDateTime") LocalDateTime startDateTime, @Param ("endDateTime") LocalDateTime endDateTime, @Param("user_id") int userId);
}
