//import java.util.UUID;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//
//public class UserServiceTest {
//
//	User user;
//	ApplicationContext ctx = new FileSystemXmlApplicationContext("spring-context.xml");
//	SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
//	
//	Session session = sf.openSession();
//	
//	@Before
//	public void setUp() throws Exception {
//		user = new User();
//		user.setUserId(UUID.randomUUID().toString());
//		user.setEmail("ledonglin@gmail.com");
//		user.setSex(true);
//		user.setUserName("ledong");
//		user.setPassword("1234QWER");
//		session.beginTransaction();
//
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testSave() {
//		session.save(user);
//		session.getTransaction().commit();
//		session.close();
//	}
//	
//	public void testGet() {
//		User u = (User) session.get(User.class, 1);
//		System.out.println(u.getEmail());
//	}
//
//}
