package net.zhoubian.app.dao;

import java.util.Iterator;

import net.zhoubian.app.model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDAOTest {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		User u = new User();
		u.setName("user1");
		u.setPassword("123456");
		session.save(u);
		tx.commit();
		Query query = session.createQuery("from User");
		Iterator it= query.iterate();
		while(it.hasNext()){
			User user = (User) it.next();
			System.out.println(user.getName());
		}
	}
	
}
