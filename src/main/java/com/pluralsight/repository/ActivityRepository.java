package com.pluralsight.repository;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pluralsight.model.Activity;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findActivity(String activityId);
	
	void addActivity(Activity a);

	Activity updateActivity(String id,Activity activity);

	boolean deleteActivity(String activityId);

	List<Activity> findByDescription(String descriptions,String exactMatch, int from, int to);



}