package com.ksl.taskmanager.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Task implements Serializable{
	private static final long serialVersionUID = -7788619177798333713L;
	static int count = 0;
	int id;
	
	int priority;
	
	public static final int LOW = 0;
	public static final int MEDIUM = 1;
	public static final int HIGH = 2;
	
	int status;
	
	public static final int STATUS_NEW = 0;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_COMPLETED = 2;
	
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

	public Task(){
		super();
	}
	
	public Task(String name) {
		super();
		this.name = name;
		assignId();
	}
	
	public Task(String id, String priority, String status, String name){
		super();
		this.id = Integer.parseInt(id);
		this.priority = Integer.parseInt(priority);
		this.status = Integer.parseInt(status);
		this.name = name;
	}
	
	public void assignId()
	{
		this.id = count++;
	}

}
