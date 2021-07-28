package com.cosmos.fax.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmos.fax.models.FaxContact;

@RestController
@RequestMapping("/api")
public class FaxAddressBookResource {
	
	ConcurrentMap<Long, FaxContact> contacts = new ConcurrentHashMap<>();
	
	@GetMapping("/{id}")
	public FaxContact getContact(@PathVariable long id) {
		return contacts.get(id);
	}
	
	@GetMapping("/")
	public List<FaxContact> getAllFaxContacts(){
		return new ArrayList<FaxContact>(contacts.values());
	}
	
	@PostMapping("/addFaxContact")
	public FaxContact addFaxContact(@RequestBody FaxContact contact) {
		contacts.put(contact.getId(), contact);
		return contact;
	}
}
