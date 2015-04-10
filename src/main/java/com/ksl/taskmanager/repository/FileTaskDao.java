package com.ksl.taskmanager.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ksl.taskmanager.domain.Task;

public class FileTaskDao implements TaskDao {
	
	HashMap<Integer, Task> tasks;
	
	public FileTaskDao()
	{
		tasks = new HashMap<Integer, Task>();
		Task t = new Task("Complete Assignment");
		tasks.put(t.getId(), t);
	}

	public Map<Integer, Task> getTasks() {
		// TODO Auto-generated method stub
		return tasks;
	}

	public void saveTask(Task t) {
		// TODO Auto-generated method stub
		tasks.put(t.getId(), t);
	}

	public Task deleteTask(int id) {
		// TODO Auto-generated method stub
		return tasks.remove(id);
		
	}

}
