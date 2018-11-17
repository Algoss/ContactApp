package com.rest.api.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.customException.ContactException;
import com.rest.api.customException.ErrorResponse;
import com.rest.api.model.Contact;
import com.rest.api.service.ContactService;
import com.rest.api.util.ContactUtil;

@RestController
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping(value = "/getAllContacts", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Contact> getContacts() throws ContactException{
		
		List<Contact> listOfContacts = new ArrayList<>();
		try {
			listOfContacts = contactService.getAllContacts();
		}
		catch(Exception e)
		{
			throw new ContactException("Error while getting the contacts");
		}
		
		if(listOfContacts.isEmpty())
			throw new ContactException("No contacts found!!");
		return listOfContacts;
	}

	@RequestMapping(value = "/getContactByEmail", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Contact> getContactByEmail(@RequestParam("email") String email) throws ContactException {
		Contact contact = null;
		try {
	      contact = contactService.getContactByEmail(email);
		}
		catch(Exception e)
		{
			if(e instanceof ContactException)
				throw new ContactException(e.getMessage());
			throw new ContactException("Error while getting the contacts");
		}
		
		return new ResponseEntity<Contact>(contact,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getContactByName", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Contact> getContactByName(@RequestParam("name") String name) throws ContactException {
		List<Contact> contacts = null;
		try {
	      contacts = contactService.getContactByName(name);
		}
		catch(Exception e)
		{
			throw new ContactException("Error while getting the contacts");
		}
		if(contacts == null || contacts.isEmpty())
			throw new ContactException("No contacts found!!");
		return contacts;
	}

	@RequestMapping(value = "/addContact", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addCountry(@RequestBody Contact contact) throws ContactException {
		try {
		ContactUtil.validateContact(contact);
		contactService.addContact(contact);
		}
		catch(Exception e)
		{
			if(e instanceof ContactException)
				throw new ContactException(e.getMessage());
			throw new ContactException("Error while adding contact: "+e.getMessage());
		}
		
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateContact(@RequestBody Contact contact) throws ContactException {
		try {
			ContactUtil.validateContact(contact);
			contactService.updateContact(contact);
			}
			catch(Exception e)
			{
				if(e instanceof ContactException)
					throw new ContactException(e.getMessage());
				throw new ContactException("Error while updating contact:"+e.getMessage());
			}
			
	}

	@RequestMapping(value = "/deleteContact({email})", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteContact(@PathVariable("email") String email) throws ContactException {
		try {
			contactService.deleteContact(email);
			}
			catch(Exception e)
			{
				if(e instanceof ContactException)
					throw new ContactException(e.getMessage());
				throw new ContactException("Error while deleting contact: "+e.getMessage());
			}		
	}
	
	@ExceptionHandler(ContactException.class)
	
	    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
	
	        ErrorResponse error = new ErrorResponse();
	
	        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
	
	        error.setMessage(ex.getMessage());
	
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	
	    }


}
