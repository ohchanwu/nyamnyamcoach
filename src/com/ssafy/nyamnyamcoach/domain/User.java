package com.ssafy.nyamnyamcoach.domain;

import java.util.*;
import java.time.*;

class User {
	String id;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	String password;
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	int height;
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	
	int weight;
	public int getWeight() { return weight; }
	public void setWeight(int weight) { this.weight = weight; }
	
	List<String> diseases;
	public List<String> getDiseases() { return diseases; }
	public void setDiseases(List<String> diseases) { this.diseases = diseases; }
	
	String status;
	public String getstatus() { return status; }
	public void setstatus(String status) { this.status = status; }
	
	String followingIds;
	public String getfollowingIds() { return followingIds; }
	public void setfollowingIds(String followingIds) { this.followingIds = followingIds; }
	
	String createdAt;
	public String getcreatedAt() { return createdAt; }
	public void setcreatedAt(String createdAt) { this.createdAt = createdAt; }
}