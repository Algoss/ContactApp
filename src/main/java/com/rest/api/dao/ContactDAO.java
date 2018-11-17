package com.rest.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.api.customException.ContactException;
import com.rest.api.model.Contact;

@Repository
public class ContactDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Contact> getAllContacts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Contact> contactList = session.createQuery("from Contact").list();
		return contactList;
	}

	public Contact getContactByEmail(String email) throws ContactException {
		Session session = this.sessionFactory.getCurrentSession();
		List<Contact> contactList = session.createQuery("from Contact c where c.email=:email").setString("email", email).list();
		if(contactList==null || contactList.isEmpty())
			throw new ContactException("No contact found!!");
		return contactList.get(0);
	}
	
	public List<Contact> getContactByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Contact> contacts = session.createQuery("from Contact c where c.name=:name").setString("name", name).list();
		return contacts;
	}

	public Contact addContact(Contact contact) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(contact);
		return contact;
	}

	public void updateContact(Contact contact) throws ContactException {
		Session session = this.sessionFactory.getCurrentSession();
		List<Contact> contacts = session.createQuery("from Contact c where c.email=:email").setString("email", contact.getEmail()).list();
		session.clear();
		if(contacts == null || contacts.isEmpty())
			throw new ContactException("contact not found!!");
		session.update(contact);
	}

	public void deleteContact(String email) throws ContactException {
		Session session = this.sessionFactory.getCurrentSession();
		List<Contact> contacts = session.createQuery("from Contact c where c.email=:email").setString("email", email).list();
		if(contacts == null || contacts.isEmpty())
			throw new ContactException("contact not found!!");
		session.clear();
		Contact p = (Contact) session.load(Contact.class, new String(email));
		if (null != p) {
			session.delete(p);
		}
	}

}
