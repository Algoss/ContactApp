package com.rest.api.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rest.api.customException.ContactException;
import com.rest.api.model.Contact;
import com.rest.api.service.ContactService;

@Category(Dummy.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath*:spring-servlet.xml"})
public class ContactServiceTest {
	
	@Autowired
	ContactService contactService;
	
	@Before
	public void prepareScenario() throws ContactException
	{
		contactService.deleteAllContacts();
		Contact con = new Contact("kumarirakhi2508@gmail.com","rakhi");
		contactService.addContact(con);
	}
	
	@After
	public void clearScenario() throws ContactException
	{
		contactService.deleteAllContacts();
	}
	
	
	@Test
	public void getAllContactsTest()
	{
		
		assertEquals(contactService.getAllContacts().size(), 1);
	}
	
	@Test
	public void checkNoContactTest() throws ContactException
	{
		contactService.deleteAllContacts();
		assertEquals(contactService.getAllContacts().size(), 0);
	}
	
	@Test
	public void getContactByEmailTest() throws ContactException
	{
		Contact contact = new Contact("kumarirakhi2508@gmail.com","rakhi");
		assertEquals(contactService.getContactByEmail("kumarirakhi2508@gmail.com").getName(), contact.getName());
	}
	
	@Test
	public void getContactByNameTest() throws ContactException
	{
		Contact contact = new Contact("kumarirakhi2508@gmail.com","rakhi");
		assertEquals(contactService.getContactByName("rakhi").get(0).getEmail(), contact.getEmail());
	}
}
