package mx.ipn.pos_support_system_api.entitys.product_control.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
  @Id
  private String bar_code;
  private String name;
  private double price;
  private String description;
  private int stock;
  private int department_id;
  private int type_id;

  public Product() {

  }

  public Product(String bar_code, String name, double price, String description,
                 int stock, int department_id, int type_id) {
    this.bar_code = bar_code;
    this.name = name;
    this.price = price;
    this.description = description;
    this.stock = stock;
    this.department_id = department_id;
    this.type_id = type_id;
  }

  public String getBar_code() {
    return bar_code;
  }

  public void setBar_code(String bar_code) {
    this.bar_code = bar_code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getDepartment_id() {
    return department_id;
  }

  public void setDepartment_id(int department_id) {
    this.department_id = department_id;
  }

  public int getType_id() {
    return type_id;
  }

  public void setType_id(int type_id) {
    this.type_id = type_id;
  }
}
