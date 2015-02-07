package com.dy.dy.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dj.dy.entity.Picture;
import com.dj.dy.service.PicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-context.xml" })
public class TestPicServiceImpl {
	ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"spring-context.xml");
	SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");

	@Autowired
	PicService picService;

	Picture picture;
	String pictureId;
	String userId;
	String username;
	String message = "今天天气真好";
	Date date = Calendar.getInstance().getTime();

	@Before
	public void setUp() throws Exception {
		pictureId = UUID.randomUUID().toString();
		userId = UUID.randomUUID().toString();
		username = "ledong";
		picture = new Picture(pictureId, userId, username, message, 0L, 0L, date);
		picService.addPicture(picture);

	}

	@After
	public void tearDown() throws Exception {
		picService.deletePicture(pictureId);
	}

	@Test
	public void testAddAgree() {
		picService.addAgree(pictureId);
	}

	@Test
	public void testAddDisagree() {
		picService.addDisagree(pictureId);
		Picture p = picService.getPictureById(pictureId);
		Assert.assertEquals(null, 1, p.getDisagree());
	}

	@Test
	public void testGetPic() {
		Picture pic = picService.getPictureById(pictureId);
		Assert.assertEquals("Equal", pic.getMessage(), message);
		Assert.assertEquals("Equal", pic.getUserId(), userId);
	}

	@Test
	public void testListPic() {

		List<Picture> list = picService.getPictures();
		for (Picture p : list)
			System.out.println(p.getPictureId() + p.getMessage());
	}
	@Test
	public void testGetAgree() {
		long number = picService.getAgree(pictureId);
		Assert.assertEquals("Equal", 0, number);
		
		picService.addAgree(pictureId);
		number = picService.getAgree(pictureId);
		Assert.assertEquals("Equal", 1, number);
		
		picService.addAgree(pictureId);
		number = picService.getAgree(pictureId);
		Assert.assertEquals("Equal", 2, number);
		
		picService.rollAgree(pictureId);
		number = picService.getAgree(pictureId);
		Assert.assertEquals("Equal", 1, number);
		
		picService.rollAgree(pictureId);
		number = picService.getAgree(pictureId);
		Assert.assertEquals("Equal", 0, number);
	}

}
