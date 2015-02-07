package com.dj.dy.dao;

import java.util.List;

import com.dj.dy.entity.Comment;

public interface CommentDao {

	public void addComment(Comment comment);

	public void deleteComment(String commentId);

	public Comment getCommentById(String commentId);

	public List<Comment> getPicComments(String picId);

	public List<Comment> getCommentsByUserId(String userId);

	public List<Comment> getCommentsByFavourite();

	public List<Comment> getCommentsByFavourite(int limit);

	public List<Comment> getPicComments(String picId, String order);

	public List<Comment> getCommentsByUserId(String userId, String order);

	public List<Comment> getCommentsByFavourite(int start, int limit);

	public void addCommentAgree(String commentId);

	public void addCommentDisagree(String commentId);

	public void rollCommentAgree(String commentId);

	public void rollCommentDisagree(String commentId);
}
