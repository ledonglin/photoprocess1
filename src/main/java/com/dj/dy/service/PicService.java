package com.dj.dy.service;

import java.util.List;

import com.dj.dy.entity.Picture;

public interface PicService {

	public void addAgree(String picId);

	public long getAgree(String picId);

	public void addDisagree(String picId);

	public long getDisAgree(String picId);

	public void rollAgree(String picId);

	public void rollDisAgree(String picId);

	public void addPicture(Picture picture);

	public Picture getPictureById(String pictureId);

	public List<Picture> getPicturesByUserId(String userId);

	public List<Picture> getPicturesByUserId(String userId, String order);

	public List<Picture> getPicturesByFavourite();

	public List<Picture> getPicturesByFavourite(int limit);

	public List<Picture> getPicturesByFavourite(int start, int limit);

	public List<Picture> getPictures();

	public List<Picture> getPictures(int start, int limit);

	public void deletePicture(String pictureId);
}
