package com.dj.dy.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.dy.entity.Picture;
import com.dj.dy.entity.User;
import com.dj.dy.service.PicService;

@Controller
@RequestMapping("/picture")
public class PictureController extends BaseController {

	@Autowired
	PicService picService;

	@RequestMapping("/addAgree/{picId}")
	public void addAgree(@PathVariable String picId) {
		this.picService.addAgree(picId);
	}

	@RequestMapping("/rollAgree/{picId}")
	public void rollAgree(@PathVariable String picId) {
		this.picService.rollAgree(picId);
	}

	@RequestMapping("/addDisagree/{picId}")
	public void addDisagree(@PathVariable String picId) {
		this.picService.addDisagree(picId);
	}

	@RequestMapping("/rollDisagree/{picId}")
	public void rollDisagree(@PathVariable String picId) {
		this.picService.rollDisAgree(picId);
	}

	@RequestMapping("/addPicture")
	public void addPicture(HttpServletRequest request, String[] message) {
		// TODO save the image in file system
		
		String pictureId = UUID.randomUUID().toString();
		User user = getSessionUser(request);
		Picture pic = new Picture();
		pic.setPictureId(pictureId);
		pic.setUserId(user.getUserId());
		pic.setUsername(user.getUserName());
		StringBuilder sb = new StringBuilder();
		for(String msg : message)
			sb.append(msg);
		pic.setMessage(sb.toString());
		pic.setDate(new Date(System.currentTimeMillis()) );
		
		this.picService.addPicture(pic);
		
	}
	@RequestMapping("/deletePicture/{picId}")
	public void deletePicture(@PathVariable String picId) {
		this.picService.deletePicture(picId);
		// TODO delete the image in file system
	}

	@RequestMapping("/getPicture/{start}-{limit}")
	public @ResponseBody List<Picture> getPicture(@PathVariable int start, @PathVariable int limit) {
		return this.picService.getPictures(start, limit);
	}

	@RequestMapping("/getPictureById/{picId}")
	public @ResponseBody Picture getPictureById(@PathVariable String picId) {
		return this.picService.getPictureById(picId);
	}

	@RequestMapping("/getPictureByUserId/{userId}")
	public @ResponseBody List<Picture> getPictureByUserId(@PathVariable String userId) {
		return this.picService.getPicturesByUserId(userId);
	}

	@RequestMapping("/getPictureByUserId/{userId}/{order}")
	public @ResponseBody List<Picture> getPictureByUserId(@PathVariable String userId, @PathVariable String order) {
		return this.picService.getPicturesByUserId(userId, order);
	}

	@RequestMapping("/getPicturesByFavourite/{limit}")
	public @ResponseBody List<Picture> getPicturesByFavourite(@PathVariable int limit){
		return this.picService.getPicturesByFavourite(limit);
		
	}

	@RequestMapping("/getPictureByUserId/{start}-{limit}")
	public @ResponseBody List<Picture> getPicturesByFavourite(@PathVariable int start, @PathVariable int limit){
		return this.picService.getPicturesByFavourite(start, limit);
	}

}
