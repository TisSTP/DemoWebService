package com.demo.res;

import java.util.HashMap;

public class ContactManager {

  private static ContactManager man;

  public static ContactManager getInstance() {
    if (man == null) {
      man = new ContactManager();
    }
    return man;
  }

  public HashMap<String, ResContact> dict;

  public ContactManager() {
    this.dict = new HashMap<>();
  }

  public HashMap<String, ResContact> getDict() {
    return dict;
  }
}
