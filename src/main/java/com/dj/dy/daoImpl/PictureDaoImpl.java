package com.dj.dy.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dj.dy.dao.BaseHibernateDAO;
import com.dj.dy.dao.PictureDao;
import com.dj.dy.entity.Picture;

@Repository
public class PictureDaoImpl extends BaseHibernateDAO implements PictureDao {

	private final String GET_AGREE_MOUNT = "select p.agree from Picture p where p.pictureId = ?";
	private final String GET_DISAGREE_MOUNT = "select p.disagree from Picture p where p.pictureId = ?";
	private final String UPDATE_AGREE_MOUNT = "update Picture p set p.agree = p.agree+1 where p.pictureId = ?";
	private final String UPDATE_DISAGREE_MOUNT = "update Picture p set p.disagree = p.disagree+1 where p.pictureId = ?";
	private final String ROLL_AGREE_MOUNT = "update Picture p set p.agree = p.agree-1 where p.pictureId = ?";
	private final String ROLL_DISAGREE_MOUNT = "update Picture p set p.disagree = p.disagree-1 where p.pictureId = ?";
	private final String DELETE_PIC = "delete Picture p where p.pictureId = ?";

	@Override
	public void addPicture(Picture picture) {
		getSession().save(picture);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Picture getPictureById(String pictureId) {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.add(Restrictions.eq("pictureId", pictureId)).list();
		if (pictureList == null || pictureList.size() == 0)
			return null;
		else
			return pictureList.get(0);
	}

	@Override
	public List<Picture> getPicturesByUserId(String userId) {
		return getPicturesByUserId(userId, "desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> getPicturesByUserId(String userId, String order) {
		Order orderType = "desc".equalsIgnoreCase(order) ? Order.desc("date")
				: Order.asc("date");

		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.add(Restrictions.eq("userId", userId)).addOrder(orderType)
				.list();

		if (pictureList == null)
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Picture> getPicturesByFavourite() {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.addOrder(Order.desc("agree")).list();

		if (pictureList == null)
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@Override
	public List<Picture> getPicturesByFavourite(int limit) {
		return getPicturesByFavourite(0, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> getPicturesByFavourite(int start, int limit) {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.addOrder(Order.desc("agree")).setFirstResult(start)
				.setFetchSize(limit).list();

		if (pictureList == null)
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> getPictures() {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.addOrder(Order.desc("date")).list();
		if (pictureList == null)
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> getPictures(int start, int limit) {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.addOrder(Order.desc("date")).setFirstResult(start)
				.setFetchSize(limit).list();
		if (pictureList == null)
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@Override
	public void addPictureAgree(String pictureId) {
		getSession().createQuery(UPDATE_AGREE_MOUNT).setParameter(0, pictureId)
				.executeUpdate();
	}

	@Override
	public void addPictureDisagree(String pictureId) {
		getSession().createQuery(UPDATE_DISAGREE_MOUNT)
				.setParameter(0, pictureId).executeUpdate();

	}

	@Override
	public void rollPictureAgree(String pictureId) {
		getSession().createQuery(ROLL_AGREE_MOUNT).setParameter(0, pictureId)
				.executeUpdate();
	}

	@Override
	public void rollPictureDisagree(String pictureId) {
		getSession().createQuery(ROLL_DISAGREE_MOUNT)
				.setParameter(0, pictureId).executeUpdate();

	}

	@Override
	public void deletePicture(String pictureId) {
		getSession().createQuery(DELETE_PIC).setParameter(0, pictureId)
				.executeUpdate();
	}

	@Override
	public long getAgree(String picId) {
		List<?> list = getSession().createQuery(GET_AGREE_MOUNT).setParameter(0, picId).list();
		if(list != null)
			return  (long) list.get(0);
		else 
			return 0;
	}

	@Override
	public long getDisAgree(String picId) {
		List<?> list = getSession().createQuery(GET_DISAGREE_MOUNT).setParameter(0, picId).list();
		if(list != null)
			return  (long) list.get(0);
		else 
			return 0;
	}

}
