package com.eteration.swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class AdressBookResource {

    ConcurrentHashMap<String,Contact> contacts = new ConcurrentHashMap<>();



	@ApiOperation(value = "Retrieve a contact with an ID", response = Contact.class)
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable String id){
        return contacts.get(id);

    }

    @ApiOperation(value = "View all contacts in the address book", response = Contact.class, responseContainer = "List")
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved the list!"),
		@ApiResponse(code = 401, message = "Not authorized to view the list!"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden!"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found!"),

	})
    @GetMapping("/")
    public List<Contact> getAllContacts(){
        return new ArrayList<Contact>(contacts.values());

    }


	@ApiOperation(value = "Add a contact", response = Contact.class)
    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact){
    
        contacts.put(contact.getId(), contact);
        return contact;
 
    }
}