package com.ksl.taskmanager.domain;

public class Task {
	static int count = 0;
	int id;
	public int getId() {
		return id;
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
		this.id = count++;
	}
	

}
