package com.how2java.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import com.how2java.pojo.User;

public class TestHibernate {
	public static void main(String[] args) {
		testCascade();
		//testTransaction();
		//testMany2Many();
		//testOne2Many();
		//testMany2One();
		//testUpdateProduct();
		//testDelProduct();
		//testGetProduct();
		//testCategory();
		//testInsertProduct();
	}
	/**
	 * 测试级联删除
	 */
	private static void testCascade() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Category c = (Category) s.get(Category.class, 3);
		s.delete(c);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 测试事物
	 */
	private static void testTransaction() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Product p1 = (Product) s.get(Product.class, 1);
		s.delete(p1);
		
		Product p2 = (Product) s.get(Product.class, 2);
		p2.setName("超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符超过30个字符");
		s.update(p2);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 新增用户
	 */
	private static void testMany2Many() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();
		
		Set<User> users = new HashSet();
		for(int i = 1; i < 4; i++) {
			User u = new User();
			u.setName("user" + i);
			users.add(u);
			s.save(u);
		}
		
		Product p = (Product) s.get(Product.class, 1);
		p.setUsers(users);
		s.update(p);

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 一对多演示
	 */
	public static void testOne2Many() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
         
        Category c = (Category) s.get(Category.class, 2);
        Set<Product> ps = c.getProducts();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
	
	/**
	 * 多对一演示
	 */
	private static void testMany2One(){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Category c = new Category();
		c.setName("c1");
		s.save(c);
		
		Product p = (Product) s.get(Product.class, 8);
		p.setCategory(c);
		s.update(p);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 类目测试
	 */
	private static void testCategory() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();

		Category c = new Category();
		c.setName("分类1");
		s.save(c);

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 根据ID修改单条商品数据
	 */
	private static void testUpdateProduct() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Product p = (Product) s.get(Product.class, 1);
		p.setName("iphone");
		s.update(p);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 根据ID删除单条商品数据
	 */
	private static void testDelProduct() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Product p = (Product) s.get(Product.class, 6);
		s.delete(p);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 根据ID获取单条商品数据
	 */
	private static void testGetProduct() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Product p = (Product) s.get(Product.class, 6);
		System.out.println("id为6的名称是："  + p.getName());
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 商品测试
	 */
	private static void testInsertProduct() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();

		for (int i = 0; i < 10; i++) {
            Product p = new Product();
            p.setName("iphone"+i);
            p.setPrice((i + 1) * 10);
            s.save(p);         
        }

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}