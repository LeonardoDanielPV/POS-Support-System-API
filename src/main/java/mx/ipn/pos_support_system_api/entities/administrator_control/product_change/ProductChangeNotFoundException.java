package mx.ipn.pos_support_system_api.entities.administrator_control.product_change;

public class ProductChangeNotFoundException extends RuntimeException {
  ProductChangeNotFoundException(Integer id) {
    super("Could not find product change " + id);
  }
}
