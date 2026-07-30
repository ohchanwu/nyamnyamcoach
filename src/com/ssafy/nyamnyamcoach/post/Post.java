package com.ssafy.nyamnyamcoach.post;

import java.util.Date;

public class Post {
	private int PostId;
	private String title;
	private String content;
	private int userId;
	private int viewCnt;
	private Date regDate;
	
	Post() {};

	public String toString() {
		return String.format("Post: %d | %s | %s | %d | %d | %s | %n", PostId, title, title, userId, viewCnt, regDate.toString());
	};

	public int getId() { return PostId; }
	public String getTitle() { return title; }
	public String getContent() { return content; }
	public int getUserId() { return userId; }
	public int getViewCnt() { return viewCnt; }
	public Date getRegDate() { return regDate; }
	
	public void setPostId(int PostId) { this.PostId = PostId; }
	public void setTitle(String title) { this.title = title; }
	public void setContent(String content) { this.content = content; }
	public void setUserId(int userSeq) { this.userId = userSeq; }
	public void setViewCnt(int viewCnt) { this.viewCnt = viewCnt; }
	public void setRegDate(Date regDate) { this.regDate = regDate; }
}