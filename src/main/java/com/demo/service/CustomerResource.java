package com.demo.service;

import com.demo.res.CustomerManager;
import com.demo.res.ResCustomer;
import java.util.HashMap;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class CustomerResource {

  private String customerid;
  HashMap<String, ResCustomer> dict;
  private final ResCustomer res;

  public CustomerResource(String customerid) {
    this.customerid = customerid;
    this.dict = CustomerManager.getInstance().getDict();
    this.res = this.dict.get(this.customerid);
  }

  public static CustomerResource getInstance(String id) {
    return new CustomerResource(id);
  }

  @GET
//  @RolesAllowed("Admins")
  @Produces(MediaType.APPLICATION_JSON)
  public ResCustomer getJson() {
    return this.res;
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void putJson(ResCustomer customer) {

  }

  @DELETE
  public void delete() {

  }

}
