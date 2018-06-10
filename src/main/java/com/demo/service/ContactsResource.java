package com.demo.service;

import com.demo.res.ContactManager;
import com.demo.res.ResContact;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("contacts")
public class ContactsResource {

  @Context
  private UriInfo context;

  HashMap<String, ResContact> myDict;

  public ContactsResource() {
    myDict = ContactManager.getInstance().getDict();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ResContact> getJson() {
    ArrayList<ResContact> list = new ArrayList<>();

    for (Map.Entry<String, ResContact> entry : myDict.entrySet()) {
      String key = entry.getKey();
      ResContact value = entry.getValue();
      list.add(value);
    }

    return list;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response postJson(ResContact content) {
    this.myDict.put(content.getFirstname(), content);
    return Response.created(context.getAbsolutePath()).build();
  }

  @Path("{id}")
  public ContactResource getContactResource(@PathParam("id") String id) {
    return ContactResource.getInstance(id);
  }

}
