package com.demo.service;

import com.demo.res.ContactManager;
import com.demo.res.ResContact;
import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ContactResource {

  private String id;
  private ResContact contact;

  public ContactResource(String id) {
    this.id = id;
    HashMap<String, ResContact> myDict = ContactManager.getInstance().getDict();
    this.contact = myDict.get(id);
  }

  public static ContactResource getInstance(String id) {
    return new ContactResource(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ResContact getJson() {
    return contact;
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putJson(ResContact contact) {
    ContactManager.getInstance().getDict().put(this.id, contact);
    return Response.ok(contact).build();
  }

  @DELETE
  public void delete() {
    ContactManager.getInstance().getDict().remove(this.id);
  }
}
