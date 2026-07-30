package com.ssafy.nyamnyamcoach.domain;

import java.util.*;
import java.time.*;

class User {
	private String id;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	private String password;
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	private int height;
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	
	private int weight;
	public int getWeight() { return weight; }
	public void setWeight(int weight) { this.weight = weight; }
	
	private List<String> diseases;
	public List<String> getDiseases() { return diseases; }
	public void setDiseases(List<String> diseases) { this.diseases = diseases; }
	
	private String status;
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	
	private String followingIds;
	public String getFollowingIds() { return followingIds; }
	public void setFollowingIds(String followingIds) { this.followingIds = followingIds; }
	
	private String createdAt;
	public String getCreatedAt() { return createdAt; }
	public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}