package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernatePackage.HibernateUtil;

@Entity
@Table(name = "Customer")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {

	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;

	private Address address = new Address();

	// public Order orderOfOrder;

	public Customer() {
	}

	// public Customer(String userName, String password, String name,
	// String surname, ArrayList<Address> addresses) {
	// super(userName,password);
	//
	// this.name = name;
	// this.surname = surname;
	// this.addresses = addresses;
	// this.isOwner=false;
	// User.customerList.add(this);
	// this.writeOnText();
	//
	// }
	public Customer(String userName, String password, String name, String surname, Address address) {
		super(userName, password);

		this.name = name;
		this.surname = surname;
		this.address = address;
		this.isOwner = false;
		
		
		this.addCusDB();
		User.customerList.add(this);
		this.writeOnText();
		System.out.println("new cust is created with id : "+this.id);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void addCusDB() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

		session.save(this);

		// Commit the transaction
		session.getTransaction().commit();
	}
	public static void delCusDB(Customer c) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("delete Order where userID = :ID");
		query.setParameter("ID", c.id);
		 
		int result = query.executeUpdate();
		
		session.delete(c);

		// Commit the transaction
		session.getTransaction().commit();
	}
	
	public static void updateCusDB(Customer c, Customer temp,boolean own) {
		Customer newCustomer = new Customer();
		newCustomer.id = c.id;
		if(!own)
			newCustomer.setUsername(temp.getUsername());
		else
			newCustomer.setUsername(c.getUsername());
		newCustomer.setPassword(temp.getPassword());
		newCustomer.setName(temp.getName());
		newCustomer.setSurname(temp.getSurname());
		newCustomer.setAddress(temp.getAddress());
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

//		session.delete(p);
		session.merge(newCustomer);

		// Commit the transaction
		session.getTransaction().commit();
	}

}
