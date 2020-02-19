package com.how2java.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.how2java.pojo.Category;
import com.how2java.pojo.Order;
import com.how2java.pojo.Product;
import com.how2java.pojo.User;

public class TestHibernate {
	public static void main(String[] args) {
		testAnnotation();
		//testLock();
		//testTotal();
		//testPage();
		//testCascade();
		// testTransaction();
		// testMany2Many();
		// testOne2Many();
		// testMany2One();
		// testUpdateProduct();
		// testDelProduct();
		// testGetProduct();
		// testCategory();
		// testInsertProduct();
	}
	/**
	 * 使用注解代替配置文件
	 */
	private static void testAnnotation() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		Order o = new Order();
		o.setPrice(100f);
		System.out.println(o);
		s.save(o);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 乐观锁测试
	 */
	private static void testLock() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s1 = sf.openSession();
        Session s2 = sf.openSession();
 
        s1.beginTransaction();
        s2.beginTransaction();
 
        Product p1 = (Product) s1.get(Product.class, 1);
        System.out.println("产品原本价格是: " + p1.getPrice());
 
        p1.setPrice(p1.getPrice() + 1000);
 
        Product p2 = (Product) s2.get(Product.class, 1);
        p2.setPrice(p2.getPrice() + 1000);
 
        s1.update(p1);
        s2.update(p2);
 
        s1.getTransaction().commit();
        s2.getTransaction().commit();
 
        Product p = (Product) s1.get(Product.class, 1);
 
        System.out.println("经过两次价格增加后，价格变为: " + p.getPrice());
 
        s1.close();
        s2.close();
 
        sf.close();
	}
	
	/**
	 * 获取total测试
	 */
	private static void testTotal() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		String name = "iphone";
		Query q = s.createQuery("select count(*) from Product p where p.name like ?");
		q.setString(0, "%"+name+"%");
		long total = (long) q.uniqueResult();
		System.out.println("total: "+ total);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

	/**
	 * 分页测试
	 */
	private static void testPage() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();

		String name = "iphone";

		Criteria c = s.createCriteria(Product.class);
		c.add(Restrictions.like("name", "%" + name + "%"));
		c.setFirstResult(2);
		c.setMaxResults(5);

		List<Product> ps = c.list();
		for (Product p : ps) {
			System.out.println(p.getName());
			System.out.println(p.getUsers());
		}

		s.getTransaction().commit();
		s.close();
		sf.close();
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
		for (int i = 1; i < 4; i++) {
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
	private static void testMany2One() {
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
		System.out.println("id为6的名称是：" + p.getName());

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
			p.setName("iphone" + i);
			p.setPrice((i + 1) * 10);
			s.save(p);
		}

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}