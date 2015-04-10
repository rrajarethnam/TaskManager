package com.ksl.taskmanager.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
		Map<Integer, Task> tasks = taskDao.getTasks();
		return tasks.get(id);
	}
	
	
	@RequestMapping(value=TaskManagerURIConstants.CREATE_TASK, method=RequestMethod.POST)
	public @ResponseBody Task createTask(@RequestBody Task task)
	{
		task.assignId();
		taskDao.saveTask(task);
		return task;
	}
	
	@RequestMapping(value=TaskManagerURIConstants.UPDATE_TASK, method=RequestMethod.PUT)
	public @ResponseBody Task updateTask(@PathVariable("id") int id, @RequestBody Task task)
	{
		task.setId(id);
		taskDao.saveTask(task);
		return task;
	}

	@RequestMapping(value=TaskManagerURIConstants.DELETE_TASK, method=RequestMethod.DELETE)
	public @ResponseBody String deleteTask(@PathVariable("id") int id)
	{
		String s = "deletion failed";
		if( taskDao.deleteTask(id) != null)
			s = "deleted";
		return s;
		
	}
	
	@RequestMapping(value=TaskManagerURIConstants.TASKS, method=RequestMethod.GET)
	public @ResponseBody List<Task> getTasks()
	{
		ArrayList<Task> tasks = new ArrayList<Task>();
		Collection<Task> saved_tasks = taskDao.getTasks().values();

		for(Task t: saved_tasks)
		{
			tasks.add(t);
		}

		return tasks;
	}
	
	@RequestMapping(value=TaskManagerURIConstants.FILTER_TASKS, method=RequestMethod.GET)
	public @ResponseBody List<Task> getTasksFiltered(@PathVariable("priority") int priority)
	{
		Collection<Task> tasks = taskDao.getTasks().values();
		List<Task> filteredTasks = new ArrayList<Task>();
		for(Task t: tasks){
			if(t.getPriority() == priority)
				filteredTasks.add(t);
		}
		return filteredTasks;
	}
	
	
}
