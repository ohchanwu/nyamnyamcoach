package com.ssafy.nyamnyamcoach.user;

import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ssafy.nyamnyamcoach.util.*;

import static com.ssafy.nyamnyamcoach.Helper.*;

import java.io.*;

public class UserManager {
//	public void initUsers() throws IOException {
//		try {
//			List<User> userList = JsonUtil.<User>fromJsonList(
//					FileUtil.read("../../../../../assets/users.json"), 
//					new TypeToken<List<User>>() {}.getType()
//			);
//			
//			for (User u : userList) {
//				users.put(u.getId(), u);
//			}
//		} catch (IOException e) {
//			throw e;
//		}
//	}
	
	private UserManager() {};
	private static final class Holder {
		private static final UserManager UM = new UserManager();
	}
	public static UserManager getUserManager() { return Holder.UM; }
	
	private HashMap<Integer, User> users = new HashMap<>();
	public HashMap<Integer, User> getUsers() { return users; }
	
	private User currentUser;
	public User getLoginUser() { return currentUser; }
	
	private HashMap<String, Integer> loginMap = new HashMap<>();
	
	public void signup(User user) throws Exception { 
		int id = generateId(users);
		users.put(id, user);
		loginMap.put(users.get(id).getName(), id);
	}
	
	public Integer createUserTemplate() throws Exception {
		int id = generateId(users);
		users.put(id, new User());
		return id;
	}
	
	public void login(String username, String password) { 
		if (!loginMap.containsKey(username)) { return; }
		int id = loginMap.get(username);
		if (users.containsKey(id)) {
			if (users.get(id).getPassword().equals(password)) {
				currentUser = users.get(id);
			}
		}
	}
	
	public void logout() { currentUser = null; }
}
