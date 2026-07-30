package com.ssafy.nyamnyamcoach;

import java.util.*;
import java.io.*;

import com.ssafy.nyamnyamcoach.post.*;
import com.ssafy.nyamnyamcoach.user.UserManager;

import static com.ssafy.nyamnyamcoach.Helper.*;

public class Main {
	static UserManager UM = UserManager.getUserManager();

	public static void main(String[] args) {
		try {
//			UM.initUsers();
			while (!blocked) {
				System.out.print(START_STRING);
				int userChoiceNum = SC.nextInt();
				switch (userChoiceNum) {
				case 1 -> {
					if (!loginInterface(5)) {
						blocked = true;
						break;
					}
					startApp();
				}
				case 2 -> signupInterface();
				case 0 -> {
					System.out.println("프로그램이 종료됩니다.");
					return;
				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void startApp() throws Exception {
		System.out.println("게시글 목록");
		List<? extends Post> Posts = PM.getPostList(0);
		if (PM.getPostCount() == 0) {
			System.out.printf("아직 게시글이 없습니다. 첫 글을 작성하시겠습니까?\n");
		}
		for (Post a : Posts){
			System.out.printf("%d %s%n", a.getId(), a.getTitle());
		}
		System.out.println(POSTS_STRING);
		switch (SC.nextInt()) {
		case 1 -> {
			writePostInterface();
			startApp();
		}
		case 2 -> {
			for (Post a : new ArrayList<>(PM.getPosts().values())) { 
				System.out.printf("%d | %s%n", a.getId(), a.getTitle()); 
			}
			int chosenPostId = SC.nextInt();
			PM.displayPost(PM.getPosts().get(chosenPostId));
		}
		case 3 -> {
			UM.logout();
			// 5 tries
			if (loginInterface(5)) {
				blocked = true;
				break;
			}
			startApp();
		}
		case 0 -> { return; }
		}
	}
}
