package com.ssafy.nyamnyamcoach.comment;

import java.util.Date;

/**
 * 댓글 정보를 나타내는 클래스
 */
public class Comment {
	private int commentId;
	private int articleId;
	private int userSeq;
	private String content;
	private Date regDate;
	
	private Comment() {}
	
	public String toString() {
		return String.format("%d | %d | %d | %s | %s | %n", commentId, articleId, userSeq, content, regDate.toString());
	}
	
	public int getCommentId() { return commentId; }
	public int getArticleId() { return articleId; }
	public int getUserSeq() { return userSeq; }
	public String getContent() { return content; }
	public Date getDate() { return regDate; }
	
	public void setCommentId(int commentId) { this.commentId = commentId; }
	public void setArticleId(int articleId) { this.articleId = articleId; }
	public void setUserSeq(int userSeq) { this.userSeq = userSeq; }
	public void setContent(String content) { this.content = content; }
	public void setDate(Date regDate) { this.regDate = regDate; }
}                                                                         