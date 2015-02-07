package com.dj.dy.dao;

import java.util.List;

import com.dj.dy.entity.Picture;

public interface PictureDao {
	public void addPicture(Picture picture);
	public void addPictureAgree(String pictureId);

	public long getAgree(String picId);
	public long getDisAgree(String picId);
	public void addPictureDisagree(String pictureId);
	public void rollPictureAgree(String pictureId);
	public void rollPictureDisagree(String pictureId);

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
