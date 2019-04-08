package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernatePackage.HibernateUtil;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;
	@Column(name = "isOwner")
	public boolean isOwner;

	// public int userID;
	// private static int idCounter = 0;

	private ArrayList<Order> orderList = new ArrayList<Order>();

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public static ArrayList<Owner> ownerList = new ArrayList<Owner>();
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static final String ownersPath = "C:\\Users\\egeus\\Desktop\\web\\owners.txt";
	public static final String customersPath = "C:\\Users\\egeus\\Desktop\\web\\customers.txt";
	// public static final String ownersPath="C:\\Users\\toshiba\\Desktop\\ders
	// notlarý\\BBM 490 - WEB\\EclipseWorkspace\\web.2\\owners.txt";
	// public static final String customersPath="C:\\Users\\toshiba\\Desktop\\ders
	// notlarý\\BBM 490 - WEB\\EclipseWorkspace\\web.2\\customers.txt";

	public User() {
	}

	public User(String username, String password) {

		this.username = username;
		this.password = password;
		// this.userID = idCounter++;
		System.out.println("uid from user: " + this.id);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void writeOnText() {
		FileOutputStream file;

		try {
			if (this.isOwner) {

				file = new FileOutputStream(ownersPath);
				ObjectOutputStream writeObj = new ObjectOutputStream(file);
				writeObj.writeObject(ownerList);
				writeObj.close();

			} else {
				file = new FileOutputStream(customersPath);
				ObjectOutputStream writeObj = new ObjectOutputStream(file);
				writeObj.writeObject(customerList);
				writeObj.close();

			}

		} catch (Exception e) {
			System.out.println("dosya bullll");
			System.out.println(e.getMessage());
		}
	}

	public static void readFromText(boolean isOwner) {

		FileInputStream file;
		try {
			if (isOwner) {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Criteria criteria = session.createCriteria(Owner.class);
				ownerList = (ArrayList<Owner>) criteria.list();

				Iterator itr = ownerList.iterator();
				while (itr.hasNext()) {

					Owner own = (Owner) itr.next();
				}

				// Commit the transaction
				session.getTransaction().commit();
//				file = new FileInputStream(ownersPath);
//				ObjectInputStream readObj = new ObjectInputStream(file);
//				ownerList = (ArrayList<Owner>) readObj.readObject();
//				readObj.close();
			} else {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Criteria criteria = session.createCriteria(Customer.class);
				customerList = (ArrayList<Customer>) criteria.list();

				Iterator itr = customerList.iterator();
				while (itr.hasNext()) {

					Customer cus = (Customer) itr.next();
				}

				// Commit the transaction
				session.getTransaction().commit();
//				file = new FileInputStream(customersPath);
//				ObjectInputStream readObj = new ObjectInputStream(file);
//				customerList = (ArrayList<Customer>) readObj.readObject();
//				readObj.close();

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean isUserNameValid(String userName) {
		boolean isValid = true;
		for (Owner o : ownerList) {
			if (o.getUsername().equalsIgnoreCase(userName))
				isValid = false;
		}
		for (Customer c : customerList) {
			if (c.getUsername().equalsIgnoreCase(userName))
				isValid = false;
		}

		return isValid;
	}

	public static User getUser(String userName) {

		for (Owner o : ownerList) {
			if (o.getUsername().equals(userName)) {

				return o;
			}
		}
		System.out.println("it is not owner");
		for (Customer c : customerList) {
			if (c.getUsername().equals(userName))
				return c;
		}
		return null;

	}

	public static Customer getCustomer(String userName) {

		for (Customer c : customerList) {
			if (c.getUsername().equals(userName))
				return c;
		}
		return null;

	}

	public static Owner getOwner(String userName) {
		for (Owner o : ownerList) {
			if (o.getUsername().equals(userName))
				return o;
		}
		return null;

	}
	public static void print() {
		for (Owner o : ownerList) {
			
			System.out.println(o.getUsername() + " " + o.id);
	
		}
	
		for (Customer c : customerList) {
			System.out.println(c.getUsername()+ " " + c.id);
		}
	}
}
