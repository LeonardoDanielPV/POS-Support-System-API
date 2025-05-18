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
  private String department;
  private String type;

  public Product() {

  }

  public Product(String bar_code, String name, double price, String description,
                 int stock, String department, String type) {
    this.bar_code = bar_code;
    this.name = name;
    this.price = price;
    this.description = description;
    this.stock = stock;
    this.department = department;
    this.type = type;
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

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
