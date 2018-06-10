package com.demo.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  private void addRestResourceClasses(Set<Class<?>> resources) {
    // and resource class here, Ex. resources.add(Demo.class);

    resources.add(ContactResource.class);
    resources.add(ContactsResource.class);
    resources.add(UsersResource.class);
  }
}
