package mx.ipn.pos_support_system_api.entitys.administrator_control.product_change;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductChange {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String bar_code;
  private String previous_name;
  private Double previous_price;
  private String previous_description;
  private Integer previous_stock;
  private Date date;

  public ProductChange() {

  }

  public ProductChange(String bar_code, String previous_name,
                       Double previous_price, String previous_description,
                       Integer previous_stock,
                       Date date) {
    this.bar_code = bar_code;
    this.previous_name = previous_name;
    this.previous_price = previous_price;
    this.previous_description = previous_description;
    this.previous_stock = previous_stock;
    this.date = date;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBar_code() {
    return bar_code;
  }

  public void setBar_code(String bar_code) {
    this.bar_code = bar_code;
  }

  public String getPrevious_name() {
    return previous_name;
  }

  public void setPrevious_name(String previous_name) {
    this.previous_name = previous_name;
  }

  public Double getPrevious_price() {
    return previous_price;
  }

  public void setPrevious_price(Double previous_price) {
    this.previous_price = previous_price;
  }

  public String getPrevious_description() {
    return previous_description;
  }

  public void setPrevious_description(String previous_description) {
    this.previous_description = previous_description;
  }

  public Integer getPrevious_stock() {
    return previous_stock;
  }

  public void setPrevious_stock(Integer previous_stock) {
    this.previous_stock = previous_stock;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
