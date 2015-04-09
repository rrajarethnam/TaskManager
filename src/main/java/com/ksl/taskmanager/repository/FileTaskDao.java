package com.ksl.taskmanager.repository;

import java.util.ArrayList;
import java.util.List;

import com.ksl.taskmanager.domain.Task;

public class FileTaskDao implements TaskDao {
	
	ArrayList<Task> tasks;
	
	public FileTaskDao()
	{
		tasks = new ArrayList<Task>();
		Task t = new Task("Complete Assignment");
		tasks.add(t);
	}

	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return tasks;
	}

	public void saveTask(Task t) {
		// TODO Auto-generated method stub
		tasks.add(t);
	}

}
