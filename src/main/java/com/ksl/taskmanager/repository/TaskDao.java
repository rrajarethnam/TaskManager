package com.ksl.taskmanager.repository;
import java.util.List;
import java.util.Map;

import com.ksl.taskmanager.domain.Task;
public interface TaskDao {
	Map<Integer, Task> getTasks();
	void saveTask(Task t);
	Task deleteTask(int id);
}
