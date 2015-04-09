package com.ksl.taskmanager.domain;

public class Task {
	static int count = 0;
	int id;
	
	int priority;
	
	public final int LOW = 0;
	public final int MEDIUM = 1;
	public final int HIGH = 2;
	
	int status;
	
	public final int STATUS_NEW = 0;
	public final int STATUS_ACTIVE = 1;
	public final int STATUS_COMPLETED = 2;
	
	public int getId() {
		return id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Task(String name) {
		super();
		this.name = name;
		assignId();
	}
	
	public void assignId()
	{
		this.id = count++;
	}

}
