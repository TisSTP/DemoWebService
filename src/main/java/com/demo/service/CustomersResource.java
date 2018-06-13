package com.demo.service;

import com.demo.res.CustomerManager;
import com.demo.res.ResCustomer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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

@Path("/customers")
//@RolesAllowed({"Admins"})
@PermitAll
public class CustomersResource {

  @Context
  private UriInfo context;
  private final HashMap<String, ResCustomer> dict;

  public CustomersResource() {
    dict = CustomerManager.getInstance().getDict();
    ResCustomer cus = new ResCustomer();
    cus.setFirstname("Sathaphorn");
    cus.setLastname("Sunthornpan");
    cus.setNickname("Tis");
    cus.setEmail("sathaphorn.stp@gmail.com");
    cus.setPhoneno("092-759-1995");
    dict.put(cus.getFirstname(), cus);
  }

  @GET
  @RolesAllowed("Admins")
//  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
  public List<ResCustomer> getJson() {
    List<ResCustomer> list = new ArrayList<>();
    for (Map.Entry<String, ResCustomer> entry : dict.entrySet()) {
      String key = entry.getKey();
      ResCustomer customer = entry.getValue();
      list.add(customer);
    }
    return list;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response postJson(ResCustomer customer) {
    return Response.created(context.getAbsolutePath()).build();
  }

  /**
   * Sub-resource locator method for {id}
   */
  @Path("{id}")
  public CustomerResource getCustomerResource(@PathParam("id") String id) {
    return CustomerResource.getInstance(id);
  }
}
