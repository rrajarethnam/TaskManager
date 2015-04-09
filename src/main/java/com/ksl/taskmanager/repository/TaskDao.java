package com.ksl.taskmanager.repository;
import java.util.List;
import com.ksl.taskmanager.domain.Task;
public interface TaskDao {
	List<Task> getTasks();
	void saveTask(Task t);
}
