package com.ksl.taskmanager.web.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksl.taskmanager.domain.Task;
import com.ksl.taskmanager.web.rest.TaskManagerURIConstants;
import com.ksl.taskmanager.repository.TaskDao;

@Controller
public class TaskManagementServiceController {
	final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	TaskDao taskDao;
	
	@RequestMapping(value=TaskManagerURIConstants.TASK, method=RequestMethod.GET)
	public @ResponseBody Task getTask(@PathVariable("id") int id)
	{
		List<Task> Tasks = taskDao.getTasks();
		Task Task = null;
		for(Task p: Tasks){
			if(p.getId() == id)
				Task = p;
		}
		return Task;
	}
	
	@RequestMapping(value=TaskManagerURIConstants.TASK, method=RequestMethod.POST)
	public @ResponseBody Task createTask(@RequestBody Task task)
	{
		task.assignId();
		taskDao.saveTask(task);
		return task;
	}
	
	@RequestMapping(value=TaskManagerURIConstants.TASKS, method=RequestMethod.GET)
	public @ResponseBody List<Task> getTasks()
	{
		return taskDao.getTasks();
	}
}
