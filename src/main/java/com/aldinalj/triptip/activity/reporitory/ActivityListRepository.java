package com.aldinalj.triptip.activity.reporitory;

import com.aldinalj.triptip.activity.model.ActivityList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivityListRepository extends JpaRepository<ActivityList, Long> {

    Optional<ActivityList> findByActivityListNameIgnoreCase (String activityListName);

    List<ActivityList> findAllByTripId(Long tripId);

    Optional<ActivityList> findById(Long id);

    Optional<ActivityList> findByIdAndTripId(Long activityListId, Long tripId);

    @Query(value = """
    SELECT al
    FROM Trip t 
    JOIN t.activityLists al 
    WHERE t.user.id = :userId
""")
    List<ActivityList> findAllLists(@Param("userId") Long userId);

}
