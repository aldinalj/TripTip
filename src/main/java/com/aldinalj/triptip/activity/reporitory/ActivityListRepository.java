package com.aldinalj.triptip.activity.reporitory;

import com.aldinalj.triptip.activity.model.ActivityList;
import com.aldinalj.triptip.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivityListRepository extends JpaRepository<ActivityList, Long> {

    Optional<ActivityList> findByActivityListNameIgnoreCase (String activityListName);

    Optional<ActivityList> findById(Long id);


    @Query(value = """
    SELECT al
    FROM Trip t 
    JOIN t.activityLists al 
    WHERE t.user.id = :userId
""")
    List<ActivityList> findAllLists(@Param("userId") Long userId);

    @Query("SELECT a FROM ActivityList a JOIN a.trip t WHERE t.id = :tripId AND t.user.id = :userId")
    List<ActivityList> findAllByTripIdAndUserId(Long tripId, Long userId);

    @Query("SELECT a FROM ActivityList a JOIN a.trip t WHERE a.id = :activityListId AND t.id = :tripId AND t.user.id = :userId")
    Optional<ActivityList> findByIdAndTripIdAndUserId(Long activityListId, Long tripId, Long userId);

    @Query("""
    SELECT al 
    FROM ActivityList al 
    WHERE al.id = :listId 
    AND al.trip.user.id = :userId
""")
    Optional<ActivityList> findByListIdAndUserId(@Param("listId") Long listId, @Param("userId") Long userId);


}
