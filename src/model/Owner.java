package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernatePackage.HibernateUtil;

@Entity
@Table(name = "Owner")
@PrimaryKeyJoinColumn(name="id")  
public class Owner extends User  {

	public Owner(){}
	public Owner(String username, String password) {
		super(username,password);
		this.isOwner=true;
		
		
		this.addOwnDB();
		User.ownerList.add(this);
		this.writeOnText();
	}
	
	public void addOwnDB() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Add new Employee object

		session.save(this);

		// Commit the transaction
		session.getTransaction().commit();
	}

}
