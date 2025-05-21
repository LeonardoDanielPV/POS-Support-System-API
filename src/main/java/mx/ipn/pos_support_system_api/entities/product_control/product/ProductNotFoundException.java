package mx.ipn.pos_support_system_api.entities.product_control.product;

public class ProductNotFoundException extends RuntimeException {
  ProductNotFoundException(String bar_code) {
    super("Could not find product " + bar_code);
  }
}
