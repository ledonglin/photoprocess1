package com.dj.dy.service;

import java.util.List;

import com.dj.dy.entity.Comment;

public interface CommentService {

	public void addAgree(String commentId);

	public void addDisagree(String CommentId);

	public void rollAgree(String CommentId);

	public void rollDisAgree(String commentId);

	public void addComment(Comment comment);
	
	public void deleteComment(String commentId);

	public void replayComment(Comment comment, String replyToCommentId);

	public Comment getCommentById(String commentId);

	public List<Comment> getPicComments(String picId);

	public List<Comment> getCommentsByUserId(String userId);

	public List<Comment> getCommentsByFavourite();

	public List<Comment> getCommentsByFavourite(int limit);

	public List<Comment> getPicComments(String picId, String order);

	public List<Comment> getCommentsByUserId(String userId, String order);

	public List<Comment> getCommentsByFavourite(int start, int limit);
}
