package com.aldinalj.triptip.activity.reporitory;

import com.aldinalj.triptip.activity.model.ActivityList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActivityListRepository extends JpaRepository<ActivityList, Long> {

    Optional<ActivityList> findByActivityListNameIgnoreCase (String activityListName);

    @Query("SELECT a.id FROM ActivityList a WHERE LOWER(a.activityListName) = LOWER(:activityListName)")
    Optional<Long> findIdByActivityListActivityListName(String activityListName);

    List<ActivityList> findAllByTripId(Long tripId);
}
