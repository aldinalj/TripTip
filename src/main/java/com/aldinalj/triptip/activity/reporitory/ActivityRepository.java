package com.aldinalj.triptip.activity.reporitory;

import com.aldinalj.triptip.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query(value = """
    SELECT a 
    FROM Trip t 
    JOIN t.activityLists al 
    JOIN al.activities a 
    WHERE t.user.id = :userId
""")
    List<Activity> findAllActivities(@Param("userId") Long userId);

    @Query("SELECT a FROM Activity a JOIN a.activityList al JOIN al.trip t WHERE al.id = :activityListId AND t.user.id = :userId")
    List<Activity> findAllByActivityListIdAndUserId(Long activityListId, Long userId);

    @Query("SELECT a FROM Activity a JOIN a.activityList al JOIN al.trip t WHERE a.id = :activityId AND al.id = :activityListId AND t.user.id = :userId")
    Optional<Activity> findByIdAndActivityListIdAndUserId(Long activityId, Long activityListId, Long userId);
}
