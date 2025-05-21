package mx.ipn.pos_support_system_api.entities.product_control.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
  
}
