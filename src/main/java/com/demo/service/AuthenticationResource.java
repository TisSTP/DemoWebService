package com.demo.service;

import com.demo.res.ResLogin;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("authentication")
public class AuthenticationResource {

  private static final Logger LOG = Logger.getLogger(AuthenticationResource.class.getName());

  @Context
  private UriInfo context;

  @Context
  private HttpServletRequest servletRequest;

  public AuthenticationResource() {
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response postLogin(@FormParam("username") String username, @FormParam("password") String password) {

    NewCookie newCookie = new NewCookie("DeeUser", username, "/", null, null, 60, false, true);

    LOG.info(username);
    LOG.info(password);

    return Response.ok("{\"token\": \"" + username + "\"}").cookie(newCookie).build();
  }

  @POST
  @Path("logout")
  public Response postLogout() {
    NewCookie newCookie = new NewCookie("DeeUser", "", "/", null, null, 60, false, true);
    return Response.ok("{\"token\": \"\"}").cookie(newCookie).build();
  }
}
