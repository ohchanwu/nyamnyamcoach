package com.ssafy.nyamnyamcoach.domain;

public class Session {
	static User currentUser;
	public static User getCurrentUser() { return currentUser; }
	public static boolean isLoggedIn() { return currentUser != null; }
}
