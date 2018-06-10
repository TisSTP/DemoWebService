package com.demo.service;

import com.demo.res.ResUser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("users")
public class UsersResource {

  @Context
  private UriInfo context;

  @Context
  private HttpServletRequest servletRequest;

  public UsersResource() {
  }


  // Request URL: http://localhost:8080/demoRest/resources/users
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ResUser> getJson() {
    List<ResUser> lists = new ArrayList<>();

    ResUser user = new ResUser();
    user.setUsername("Stitch");
    user.setFirstname("first");
    user.setLastname("last");
    lists.add(user);

    ResUser user2 = new ResUser();
    user2.setUsername("Tis");
    user2.setFirstname("Sathaphorn");
    user2.setLastname("Sunthornpan");
    lists.add(user2);

    System.out.println(context.getPath()); // users
    System.out.println(context.getAbsolutePath().toString()); // http://localhost:8080/demoRest/resources/users

    return lists;
  }


  // http://localhost:8080/demoRest/resources/users/me?user=hello
  // Header Request: Accept: application/xml Or application/json
  @GET
  @Path("/me")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public ResUser getMe() {
    // get query string
//    String username = (String) servletRequest.getAttribute("user"); // return Object
    System.out.println(servletRequest.getAttribute("user"));
    String username = servletRequest.getParameter("user"); // return String only
    System.out.println(username);

    ResUser user = new ResUser();
    user.setUsername(username);
    user.setFirstname("Sathaphorn");
    user.setLastname("Sunthornpan");

    // birthday
    Calendar calendar = Calendar.getInstance();
    calendar.set(1996, Calendar.MARCH, 13);
    Date birthday = calendar.getTime();
    user.setBirthday(birthday);

    return user;
  }


  // http://localhost:8080/demoRest/resources/users/tis
  @GET
  @Path("/{uid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getId(@PathParam("uid") String uid) {

    if ("tis".equals(uid)) {
      ResUser user = new ResUser();
      user.setUsername(uid);
      user.setFirstname("Sathaphorn");
      user.setLastname("Sunthornpan");
      return Response.ok(user).build();
    }

//    return Response.noContent().build();
    return Response.status(Status.FORBIDDEN).build();
  }
}
