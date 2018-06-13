package com.demo.res;

import java.util.HashMap;

public class CustomerManager {

  private static CustomerManager man;

  public static CustomerManager getInstance() {
    if (man == null) {
      man = new CustomerManager();
    }
    return man;
  }

  public HashMap<String, ResCustomer> dict;

  public CustomerManager() {
    dict = new HashMap<>();
  }

  public HashMap<String, ResCustomer> getDict() {
    return dict;
  }
}
