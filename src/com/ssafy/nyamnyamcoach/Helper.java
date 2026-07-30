package com.ssafy.nyamnyamcoach;

import java.util.*;
import com.ssafy.nyamnyamcoach.user.*;
import com.ssafy.nyamnyamcoach.post.*;
import com.ssafy.nyamnyamcoach.comment.*;

public class Helper {
	public static final Scanner SC = new Scanner(System.in);
	public static final UserManager UM = UserManager.getUserManager();
	public static final PostManager PM = PostManager.getPostManager();
	public static final CommentManager CM = CommentManager.getCommentManager();
	static boolean blocked = false;
	static final StringBuilder SB = new StringBuilder();
	private static final Random ID_GENERATOR = new Random();

	static void writePostInterface() throws Exception {
		System.out.println("제목 (다 쓰셨으면 <Enter>를 치시오)");
		String title = SC.nextLine();
		System.out.println("내용 (다 쓰셨으면 \" END \"를 치시오)");
		StringBuilder sb = new StringBuilder();
		while (SC.hasNext()) {
			String nextWord = SC.next();
			if (nextWord.equals("END"))
				break;
			sb.append(nextWord);
		}
		Post[] newAs = PM.addPost();
		newAs[0].setTitle(title);
		newAs[0].setContent(sb.toString());
	}

	/**
	 * loginInterface provides the initial startup user interface. The user can log
	 * in, create a new account, or end the application. It takes an integer value
	 * representing the number of times the user may try to log in before the
	 * application is shut down. It returns false if the login is unsuccessful, and
	 * true if successful.
	 */
	static boolean loginInterface(int triesLeft) {
		System.out.println("username 입력:");
		String un = SC.next();
		System.out.println("password 입력:");
		String password = SC.next();
		UM.login(un, password);
		if (UM.getLoginUser() == null) {
			if (triesLeft == 0) {
				System.out.println("저의 어플을 해킹하려고요? 허허, 어림도 없습니다.");
				blocked = true;
				return false;
			}
			if (blocked)
				return false;
			System.out.printf("로그인 실패, %d 시도가 남았습니다.%n", triesLeft);
			loginInterface(triesLeft - 1);
		}
		if (blocked)
			return false;
		System.out.println("로그인 성공");
		System.out.printf("%s님 환영합니다.%n", UM.getLoginUser().getName().toString());
		System.out.println(LINE);
		return true;
	}

	static void signupInterface() throws Exception {
		Integer id = UM.createUserTemplate();
		User newUser = UM.getUsers().get(id);
		System.out.println("비밀번호 입력");
		newUser.setPassword(SC.next());
		System.out.println("username 입력");
		newUser.setName(SC.next());

		UM.signup(newUser);

		System.out.println("회원가입 되었습니다. 로그인하십시오.");
	}
	
	public static int generateId(HashMap<Integer, ? extends Object> map) throws Exception {
		Integer id = ID_GENERATOR.nextInt(101) + 100;
		int attempts = 0;
		if (map.keySet().contains(id)) {
			attempts++;
			if (attempts > 100) {
				throw new Exception("no valid IDs remaining");
			}
			id = generateId(map);
		}
		return id;
	}

	static final String LINE = "--------------------------------------------------";

	static final String START_STRING = """
			--------------------------------------------------
			게시판 접속
			--------------------------------------------------
			작업을 선택하세요.
			1. 로그인
			2. 회원가입
			0. 종료
			--------------------------------------------------
			""";
	static final String POSTS_STRING = """
			--------------------------------------------------
			1. 게시글 작성
			2. 게시글 선택
			3. 로그아웃
			0. 종료
			--------------------------------------------------
			""";
}