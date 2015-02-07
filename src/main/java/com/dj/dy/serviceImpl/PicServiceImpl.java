package com.dj.dy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.dy.dao.PictureDao;
import com.dj.dy.entity.Picture;
import com.dj.dy.service.PicService;

@Service
public class PicServiceImpl implements PicService {

	@Autowired
	private PictureDao pictureDao;
	
	@Override
	public void addAgree(String picId) {
		this.pictureDao.addPictureAgree(picId);
	}

	@Override
	public void addDisagree(String picId) {
		this.pictureDao.addPictureDisagree(picId);
	}

	@Override
	public void rollAgree(String picId) {
		this.pictureDao.rollPictureAgree(picId);
	}

	@Override
	public void rollDisAgree(String picId) {
		this.pictureDao.rollPictureDisagree(picId);
	}

	@Override
	public void addPicture(Picture picture) {
		this.pictureDao.addPicture(picture);		
	}

	@Override
	public Picture getPictureById(String pictureId) {
		return this.pictureDao.getPictureById(pictureId);
	}

	@Override
	public List<Picture> getPicturesByUserId(String userId) {
		return this.pictureDao.getPicturesByUserId(userId);
	}

	@Override
	public List<Picture> getPicturesByUserId(String userId, String order) {
		return this.pictureDao.getPicturesByUserId(userId, order);
	}

	@Override
	public List<Picture> getPicturesByFavourite() {
		return this.pictureDao.getPicturesByFavourite();
	}

	@Override
	public List<Picture> getPicturesByFavourite(int limit) {
		return this.pictureDao.getPicturesByFavourite(limit);
	}

	@Override
	public List<Picture> getPicturesByFavourite(int start, int limit) {
		return this.pictureDao.getPictures(start, limit);
	}

	@Override
	public List<Picture> getPictures() {
		return this.pictureDao.getPictures();
	}

	@Override
	public List<Picture> getPictures(int start, int limit) {
		return this.pictureDao.getPictures(start, limit);
	}

	@Override
	public void deletePicture(String pictureId) {
		this.pictureDao.deletePicture(pictureId);
	}

	@Override
	public long getAgree(String picId) {
		return this.pictureDao.getAgree(picId);
	}

	@Override
	public long getDisAgree(String picId) {
		return this.pictureDao.getDisAgree(picId);
	}

	
}
