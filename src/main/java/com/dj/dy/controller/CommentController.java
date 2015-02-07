package com.dj.dy.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.dy.entity.Comment;
import com.dj.dy.entity.User;
import com.dj.dy.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(CommentController.class);

	// @RequestMapping(value = "/add", method = RequestMethod.POST)
	// public @ResponseBody JsonEntity addComments(Comment comment) {
	// return new JsonEntity();
	// }
	@Autowired
	private CommentService commentService;

	@RequestMapping("/addAgree/{commentId}")
	public void addAgree(@PathVariable String commentId) {
		this.commentService.addAgree(commentId);
	}

	@RequestMapping("/rollAgree/{commentId}")
	public void rollAgree(@PathVariable String commentId) {
		this.commentService.rollAgree(commentId);
	}

	@RequestMapping("/addDisagree/{commentId}")
	public void addDisagree(@PathVariable String commentId) {
		this.commentService.addDisagree(commentId);
	}

	@RequestMapping("/rollDisagree/{commentId}")
	public void rollDisagree(@PathVariable String commentId) {
		this.commentService.rollDisAgree(commentId);
	}

	@RequestMapping("/addComment")
	public void addComment(HttpServletRequest request, Comment comment){
		User user = getSessionUser(request);
		Comment com = new Comment();
		String comId = UUID.randomUUID().toString();
		com.setCommentId(comId);
		com.setCommentUserId(user.getUserId());
		com.setCommentUsername(user.getUserName());
		com.setCommentPic(comment.getCommentPic());
		com.setDate(new Date(System.currentTimeMillis()));
		
		this.commentService.addComment(com);
	}
	@RequestMapping("/replyComment/{replyToComment}")
	public void replyComment(HttpServletRequest request, Comment comment, @PathVariable String replyToComment){
		
		User user = getSessionUser(request);
		Comment com = new Comment();
		String comId = UUID.randomUUID().toString();
		com.setCommentId(comId);
		com.setCommentUserId(user.getUserId());
		com.setCommentUsername(user.getUserName());
		com.setCommentPic(comment.getCommentPic());
		com.setCommentContext(comment.getCommentContext());
		com.setDate(new Date(System.currentTimeMillis()));
		
		this.commentService.replayComment(com, replyToComment);
	}

	@RequestMapping("/deleteComment/{commentId}")
	public void deleteComment(@PathVariable String commentId) {
		this.commentService.deleteComment(commentId);
	}

	@RequestMapping("/getCommentById/{commentId}")
	public @ResponseBody Comment getCommentById(@PathVariable String commentId) {
		return this.commentService.getCommentById(commentId);
	}

	@RequestMapping("/getPicComments/{picId}")
	public @ResponseBody List<Comment> getPicComments(@PathVariable String picId) {
		return this.commentService.getPicComments(picId);
	}
	
	@RequestMapping("/getPicComments/{picId}/{order}")
	public @ResponseBody List<Comment> getPicComments(@PathVariable String picId, @PathVariable String order){
		return this.commentService.getPicComments(picId, order);
	}

	@RequestMapping("/getCommentsByUserId/{userId}")
	public @ResponseBody List<Comment> getCommentsByUserId(@PathVariable String userId) {
		return this.commentService.getCommentsByUserId(userId);
	}

	@RequestMapping("/getCommentsByUserId/{userId}/{order}")
	public @ResponseBody List<Comment> getCommentsByUserId(@PathVariable String userId, @PathVariable String order){
		return this.commentService.getCommentsByUserId(userId, order);
	}

	@RequestMapping("/getCommentsByFavourite/{limit}")
	public @ResponseBody List<Comment> getCommentsByFavourite(@PathVariable int limit) {
		return this.commentService.getCommentsByFavourite(limit);
	}
	@RequestMapping("/getCommentsByFavourite/{start}-{limit}")
	public @ResponseBody List<Comment> getCommentsByFavourite(@PathVariable int start, @PathVariable int limit){
		return this.commentService.getCommentsByFavourite(start, limit);
	}

}
