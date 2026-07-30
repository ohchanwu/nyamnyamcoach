package com.ssafy.nyamnyamcoach.post;

import java.util.*;
import static com.ssafy.nyamnyamcoach.Helper.*;
import com.ssafy.nyamnyamcoach.comment.*;

public class PostManager {
	static private HashMap<Integer, Post> posts = new HashMap<>();
	
	private static final class Holder {
		private static final PostManager AM = new PostManager();
	}
	
	private PostManager() {};
	
	public static PostManager getPostManager() { return Holder.AM; }
	public int getPostCount() { return posts.size(); }
	public HashMap<Integer, Post> getPosts() { return posts; }
	
	/**
	 * returns all Posts if passed 0, 
	 * returns only and all text Posts if passed 1, 
	 * returns only and all image Posts if passed 2, 
	 * note: I don't think this is idiomatic Java; this is closer to idiomatic Go, 
	 * but, it's good practice for using Java generics
	 * @param mode
	 * @return
	 */
	public List<? extends Post> getPostList(int mode) { 
		return new ArrayList<>(posts.values());
	}
	
	/**
	 * Takes a variable number of Posts to add.
	 * If no Posts are passed, adds a newly created Post.
	 * @param PostsToAdd
	 */
	public Post[] addPost(Post... PostsToAdd) throws Exception { 
		Post newA = null;
		if (PostsToAdd.length == 0) {
			newA = new Post();
			posts.put(generateId(posts), newA);
			return new Post[] {newA};
		}
		for (Post a : PostsToAdd) { posts.put(generateId(posts), a); }
		return PostsToAdd;
	}
	
	public ArrayList<Post> searchPosts(String searchStr, int mode) {
		if (mode < 1 || mode > 3) throw new IllegalArgumentException("mode must be in [1, 3]");
		ArrayList<Post> result = new ArrayList<Post>();
		switch (mode) {
		case 1 -> {
			for (Post a : posts.values()) {
				if (a.getTitle().contains(searchStr)) { result.add(a); }
			}
		}
		case 2 -> {
			for (Post a : posts.values()) {
				if (a.getContent().contains(searchStr)) { result.add(a); }
			}
		}
		case 3 -> {
			for (Post a : posts.values()) {
				if (a.getUserId() == Integer.parseInt(searchStr)) { result.add(a); }
			}
		}
		}
		return result;
	}
	
	public void displayPost(Post post) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (Comment c : CM.getPostCommentList(post.getId())) {
			sb.append(count).append('.').append(' ').append(c.getContent()).append('\n');
			count++;
		}
		System.out.print(String.format("""
				--------------------------------------------------
				제목: %s
				작성자: %s
				조회수: %d
				작성일: %s
				내용: %s
				--------------------------------------------------
				총 댓글의 수 : %d
				""", post.getTitle(), UM.getUsers().get(post.getUserId()).getName(),
				post.getViewCnt(), post.getRegDate(), post.getContent(), count));
		System.out.println(sb);
		System.out.print(String.format("""
				--------------------------------------------------
				1. 게시글 삭제
				2. 댓글 작성
				3. 댓글 삭제
				0. 종료
				--------------------------------------------------
				"""));
	}
}