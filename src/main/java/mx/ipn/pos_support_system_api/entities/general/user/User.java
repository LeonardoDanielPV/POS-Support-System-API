package mx.ipn.pos_support_system_api.entities.general.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
  @Id
  private String id;
  private String first_name;
  private String last_name;
  private String user_name;
  private String password;
  private enum user_types {
    SELLER,
    ADMINISTRATOR,
    SYSTEM_ADMINISTRATOR
  }
  private user_types type;

  public User() {
    
  }

  public User(String id, String first_name, String last_name, String user_name,
              String password, user_types type) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.user_name = user_name;
    this.password = password;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public user_types getType() {
    return type;
  }

  public void setType(user_types type) {
    this.type = type;
  }
  
}
