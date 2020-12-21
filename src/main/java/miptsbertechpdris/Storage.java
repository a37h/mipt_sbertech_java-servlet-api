package miptsbertechpdris;

import java.util.concurrent.ConcurrentHashMap;

public class Storage {
  private static ConcurrentHashMap<String, String> data = new ConcurrentHashMap<> ();
  
  public static boolean usernameExists(String username) {
      return data.containsKey(username);
  }

  public static void register(String username, String password) {
      data.put(username, password);
  }

  public static boolean checkPassword(String username, String password) {
      return data.get(username).equals(password);
  }
}
