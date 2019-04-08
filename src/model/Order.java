package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

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
@Table(name = "OrderTable")
public class Order implements Serializable {
	// @Column(name= "userID")
	// public int userID;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
//	private int orderID;
	@Column(name = "status")
	private String status;
	@Column(name = "userID")
	private int userID;
	@Column(name = "productName")
	private String productName;


	public static ArrayList<Order> orderList = new ArrayList<Order>();
//	public static int idCounter = 0;
	public static final String ordersPath = "C:\\Users\\egeus\\Desktop\\web\\orders.txt";
	// public static final String ordersPath="C:\\Users\\toshiba\\Desktop\\ders
	// notlarý\\BBM 490 - WEB\\EclipseWorkspace\\web.2\\orders.txt";
	// @Column(name = "productList")
	private ArrayList<Product> productList = new ArrayList<Product>();

	public Order(){System.out.println("in DEFAULT constructorrr");}
	public Order(int uid, String proName) throws IOException {
		this.userID = uid;
		this.productName = proName;
		this.status = "new";
		orderList.add(this);
		this.writeOnText();
		this.addOrderDB();
		
		System.out.println("in  constructorrr id: "+this.getId());
	}

	

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void addProduct(Product pro) {
		productList.add(pro);
		
		System.out.println(pro.getName() + " " + this.id + " id li order a eklendi");

	}

	public void writeOnText() {
		FileOutputStream file;
		try {
			file = new FileOutputStream(ordersPath);
			ObjectOutputStream writeObj = new ObjectOutputStream(file);
			writeObj.writeObject(orderList);
			writeObj.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readFromText() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Order.class);
		orderList = (ArrayList<Order>) criteria.list();

		Iterator itr = orderList.iterator();
		while (itr.hasNext()) {

			Order order = (Order) itr.next();
		}

		// Commit the transaction
		session.getTransaction().commit();

//		FileInputStream file;
//
//		ObjectInputStream readObj;
//		try {
//			file = new FileInputStream(ordersPath);
//			readObj = new ObjectInputStream(file);
//			orderList = (ArrayList<Order>) readObj.readObject();
//			readObj.close();
////			idCounter = orderList.size();
//		} catch (IOException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	public void addOrderDB() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

		session.save(this);

		// Commit the transaction
		session.getTransaction().commit();
	}
	
	public static void updateOrderDB(Order o) {
		Order newOrder = new Order();
		newOrder.setId(o.getId());
		newOrder.setProductName(o.getProductName());
		newOrder.setUserID(o.getUserID());
		newOrder.setStatus("sent");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

//		session.delete(p);
		session.merge(newOrder);

		// Commit the transaction
		session.getTransaction().commit();
	}

}
