package mx.ipn.pos_support_system_api.entities.product_control.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler
implements RepresentationModelAssembler<Product, EntityModel<Product>> {
  @Override
  public EntityModel<Product> toModel(Product product) {
    return EntityModel.of(product,
        linkTo(methodOn(ProductController.class).one(product.getBar_code())).withSelfRel(),
        linkTo(methodOn(ProductController.class).all()).withRel("products"));
  }
}
