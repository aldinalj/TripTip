package com.aldinalj.triptip.activity.reporitory;

import com.aldinalj.triptip.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    Optional<Activity> findByActivityNameIgnoreCase (String activityName);
}
