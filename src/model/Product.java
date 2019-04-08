package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernatePackage.HibernateUtil;

@Entity
@Table(name = "Product")
public class Product implements Serializable {
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Double price;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

//	public int productID;

	public static ArrayList<Product> productList = new ArrayList<Product>();
	public static final String productsPath = "C:\\Users\\egeus\\Desktop\\web\\products.txt";
	// public static final String productsPath="C:\\Users\\toshiba\\Desktop\\ders
	// notlarý\\BBM 490 - WEB\\EclipseWorkspace\\web.2\\products.txt";
//	public static int idCounter = 0;

	public Product() {
	}

	public Product(String name2, Double price) throws IOException {
		super();
		name = name2;
		this.price = price;
		productList.add(this);
//		this.productID = idCounter++;
		this.writeOnText();
		this.addProDB();
	}

	public String getName() {
		return name;
	}

	public void setName(String name2) {
		this.name = name2;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public static Product getProductFromName(String name) {
		for (Product p : productList) {
			if (p.getName().equalsIgnoreCase(name))
				return p;
		}
		return null;
	}

	public void writeOnText() throws IOException {
		FileOutputStream file;
		file = new FileOutputStream(productsPath);
		ObjectOutputStream writeObj = new ObjectOutputStream(file);
		writeObj.writeObject(productList);
		writeObj.close();
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(productList.get(productList.size() - 1));
//		session.getTransaction().commit();
	}

	public static void readFromText() {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Product.class);
		productList = (ArrayList<Product>) criteria.list();

		Iterator itr = productList.iterator();
		while (itr.hasNext()) {

			Product pro = (Product) itr.next();
		}

		// Commit the transaction
		session.getTransaction().commit();

//		FileInputStream file;
//		try {
//			file = new FileInputStream(productsPath);
//			ObjectInputStream readObj = new ObjectInputStream(file);
//			productList = (ArrayList<Product>) readObj.readObject();
//			readObj.close();
//		} catch (IOException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	public void addProDB() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

		session.save(this);

		// Commit the transaction
		session.getTransaction().commit();
	}
	
	public static void deletePro (Product p) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

		session.delete(p);

		// Commit the transaction
		session.getTransaction().commit();
	}
	
	public static void updatePro (Product p,double newPrice) {
		Product newProduct = new Product();
		newProduct.id=p.id;
		newProduct.setName(p.getName());
		newProduct.setPrice(newPrice);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

//		session.delete(p);
		session.merge(newProduct);

		// Commit the transaction
		session.getTransaction().commit();
	}

}
