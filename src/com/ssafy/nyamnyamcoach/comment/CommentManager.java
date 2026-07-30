package com.ssafy.nyamnyamcoach.comment;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentManager {
	HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
	
	private static final class Holder {
		private static final CommentManager CM = new CommentManager();
	}
	
	private CommentManager() {};
	
	public static CommentManager getCommentManager() { return Holder.CM; }
	
	public ArrayList<Comment> getPostCommentList(int articleId) {
		ArrayList<Comment> articleCommentList = new ArrayList<>();
		for (Comment c : comments.values()) {
			articleCommentList.add(c);
		}
		return articleCommentList;
	}
}
