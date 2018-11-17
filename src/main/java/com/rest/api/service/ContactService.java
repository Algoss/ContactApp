package com.rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.api.customException.ContactException;
import com.rest.api.dao.ContactDAO;
import com.rest.api.model.Contact;

@Service("contactService")
public class ContactService {
	
	@Autowired
	ContactDAO contactDao;
	

	@Transactional
	public List<Contact> getAllContacts() {
		return contactDao.getAllContacts();
	}

	@Transactional
	public List<Contact> getContactByName(String name) {
		return contactDao.getContactByName(name);
	}
	

	@Transactional
	public Contact getContactByEmail(String email) throws ContactException {
		return contactDao.getContactByEmail(email);
	}

	@Transactional
	public void addContact(Contact contact) {
		contactDao.addContact(contact);
	}

	@Transactional
	public void updateContact(Contact contact) throws ContactException {
		contactDao.updateContact(contact);

	}

	@Transactional
	public void deleteContact(String email) throws ContactException {
		contactDao.deleteContact(email);
	}

	

}
