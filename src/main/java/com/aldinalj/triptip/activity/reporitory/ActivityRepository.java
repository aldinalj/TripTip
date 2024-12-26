package com.aldinalj.triptip.activity.reporitory;

import com.aldinalj.triptip.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    Optional<Activity> findByActivityNameIgnoreCase (String activityName);

    List<Activity> findAllByActivityListId(Long activityListId);

    Optional<Activity> findByIdAndActivityListId(Long activityId, Long activityListId);

    @Query(value = """
    SELECT a 
    FROM Trip t 
    JOIN t.activityLists al 
    JOIN al.activities a 
    WHERE t.user.id = :userId
""")
    List<Activity> findAllActivities(@Param("userId") Long userId);


}
