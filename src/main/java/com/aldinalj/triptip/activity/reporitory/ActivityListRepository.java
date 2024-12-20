package com.aldinalj.triptip.activity.reporitory;

import com.aldinalj.triptip.activity.model.ActivityList;
import com.aldinalj.triptip.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityListRepository extends JpaRepository<ActivityList, Long> {

    Optional<ActivityList> findByActivityListNameIgnoreCase (String activityListName);

    List<ActivityList> findByTripId(Long tripId);
}
