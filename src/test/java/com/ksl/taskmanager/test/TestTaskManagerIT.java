package com.ksl.taskmanager.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.ksl.taskmanager.domain.*;
import com.ksl.taskmanager.web.rest.TaskManagerURIConstants;

public class TestTaskManagerIT {
	public static final String SERVER_URI = "http://localhost:8080/TaskManager";

	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testCRUD(){

		//Task Creation Test

		Task t = new Task();
		t.setId(0);
		t.setName("Integration Test 1");
		t.setPriority(Task.HIGH);
		t.setStatus(Task.STATUS_NEW);
		
		
		Task t1 = restTemplate.postForObject(SERVER_URI+TaskManagerURIConstants.CREATE_TASK, t,Task.class);
		
		System.out.println(t.getName());
		System.out.println(t1.getName());
		
		assertTrue("Task Creation test", t.getName().equals(t1.getName()));
		assertTrue("Task Creation test", t.getPriority() == t1.getPriority());
		assertTrue("Task Creation test", t.getStatus() == t1.getStatus());
		
		//Task Get Test
		Task t2 = restTemplate.getForObject(SERVER_URI+"/rest/task/"+t1.getId(), Task.class);
		
		assertTrue("Task Get test", t1.getName().equals(t2.getName()));
		assertTrue("Task Get test", t1.getId() == t2.getId());
		assertTrue("Task Get test", t1.getPriority() == t2.getPriority());
		assertTrue("Task Get test", t1.getStatus() == t2.getStatus());
		
		//Task update Test
		t1.setStatus(Task.STATUS_COMPLETED);
		restTemplate.put(SERVER_URI+"/rest/task/"+t1.getId(), t1);
		
		Task t3 = restTemplate.getForObject(SERVER_URI+"/rest/task/"+t1.getId(), Task.class);
		assertTrue("Task update Test", t3.getStatus() == Task.STATUS_COMPLETED);
		
		//Task delete Test
		restTemplate.delete(SERVER_URI+"/rest/task/"+t1.getId());
		Task t4 = restTemplate.getForObject(SERVER_URI+"/rest/task/"+t1.getId(), Task.class);
		assertTrue("Task delete Test", t4==null);
	}
	
	@Test
	public void testGetAllTasks() {
		// TODO Auto-generated method stub
		
		HashMap<String, Task> tasks= new HashMap<String, Task>();
		
		tasks.put("New Task", new Task(0, Task.LOW, Task.STATUS_NEW, "New Task"));
		tasks.put("New Task", new Task(0, Task.LOW, Task.STATUS_NEW, "First Task"));
		tasks.put("New Task", new Task(0, Task.HIGH, Task.STATUS_NEW, "Second Task"));
		tasks.put("New Task", new Task(0, Task.MEDIUM, Task.STATUS_NEW, "Third Task"));
		tasks.put("New Task", new Task(0, Task.MEDIUM, Task.STATUS_NEW, "Fourth Task"));
		tasks.put("New Task", new Task(0, Task.HIGH, Task.STATUS_NEW, "Fifth Task"));
		tasks.put("New Task", new Task(0, Task.LOW, Task.STATUS_NEW, "Sixth Task"));
		
		for(String tname : tasks.keySet())
		{
			Task t = tasks.get(tname);
			restTemplate.postForObject(SERVER_URI+TaskManagerURIConstants.CREATE_TASK, t,Task.class);			
		}
		
		List<LinkedHashMap> tasks1 = restTemplate.getForObject(SERVER_URI+TaskManagerURIConstants.TASKS, List.class);
		for(LinkedHashMap map : tasks1)
		{
			Task t = new Task(map);
			
			assertTrue("Get All Tasks test", tasks.containsKey(t.getName()));
		}
		
		List<LinkedHashMap> filtered_tasks = restTemplate.getForObject(SERVER_URI+"/rest/tasks/priority/0", List.class);
		for(LinkedHashMap map : filtered_tasks)
		{
			Task t = new Task(map);
			assertTrue("Get Filtered Tasks test", tasks.containsKey("New Task"));
			assertTrue("Get Filtered Tasks test", tasks.containsKey("First Task"));
			assertTrue("Get Filtered Tasks test", tasks.containsKey("Fifth Task"));
		}
		
	}

}
