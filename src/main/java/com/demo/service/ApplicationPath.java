package com.demo.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("resources")
public class ApplicationPath extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  private void addRestResourceClasses(Set<Class<?>> resources) {
    // and resource class here, Ex. resources.add(Demo.class);

    resources.add(UsersResource.class);
  }
}
