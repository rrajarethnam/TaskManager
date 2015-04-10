package com.ksl.taskmanager.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

import com.ksl.taskmanager.domain.*;
import com.ksl.taskmanager.web.rest.TaskManagerURIConstants;

public class TestTaskManager {
	public static final String SERVER_URI = "http://localhost:8080/TaskManager";
	
	@Test
	public void testGetAllTasks() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> tasks = restTemplate.getForObject(SERVER_URI+TaskManagerURIConstants.TASKS, List.class);
		
		for(LinkedHashMap map : tasks)
		{
			System.out.println(map);
			assertTrue("condition must be true", map.containsKey("name"));
		}
	}

}
