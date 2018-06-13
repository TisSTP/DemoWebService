package com.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
//@PreMatching // NullPointerException: line 48
public class AuthFilter implements ContainerRequestFilter, ContainerResponseFilter {

  @Context
  private ResourceInfo resourceInfo;


  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    boolean valid = false;

    // Get Auth Info
    String token = null;
    ArrayList<String> mRoles = new ArrayList<>();
    Cookie cookie = containerRequestContext.getCookies().get("DeeUser");
    if (cookie != null) {
      token = cookie.getValue();
      if (token != null && !token.isEmpty()) {
        mRoles.add("Users");
        if ("admin".equals(token)) {
          mRoles.add("Admins");
        }
      }
      containerRequestContext.setProperty("user", token);
      containerRequestContext.setProperty("roles", mRoles);
    }

    // Allow Roles from class, method
    ArrayList<String> rRoles = new ArrayList<>();
    RolesAllowed rolesAnnotationClass = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
    RolesAllowed rolesAnnotationMethod = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
    if (rolesAnnotationClass == null && rolesAnnotationMethod == null) {
      valid = true;
    } else {
      if (rolesAnnotationMethod != null) {
        rRoles.addAll(Arrays.asList(rolesAnnotationMethod.value()));
      } else if (rolesAnnotationClass != null) {
        rRoles.addAll(Arrays.asList(rolesAnnotationClass.value()));
      }
    }

    // Check Permissions
    if (!rRoles.isEmpty()) {
      for (String rRole : rRoles) {
        if (mRoles.indexOf(rRole) > -1) {
          valid = true;
        }
      }
    }

    if (!valid) {
      // { "type": "error", "content": "You cannot access this resource." }
      Response ACCESS_DENIED = Response.status(Status.UNAUTHORIZED)
          .entity("{ \"type\": \"error\", \"content\": \"You cannot access this resource.\"}")
          .build();
      containerRequestContext.abortWith(ACCESS_DENIED);
    }
  }


  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext)
      throws IOException {

  }
}
