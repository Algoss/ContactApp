package com.rest.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rest.api.customException.ContactException;
import com.rest.api.model.Contact;

public class ContactUtil {

	public static void validateContact(Contact contact) throws ContactException {
		
		String email = contact.getEmail();
		String name = contact.getName();
		
		if(email=="" || email==null)
			throw new ContactException("Email is empty!!");
		
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        
        if(!mat.matches())
        	throw new ContactException("Email is not valid. Provide a valid email");
		
        if(name=="" || name==null)
        	throw new ContactException("name is empty!!");
	}

}
