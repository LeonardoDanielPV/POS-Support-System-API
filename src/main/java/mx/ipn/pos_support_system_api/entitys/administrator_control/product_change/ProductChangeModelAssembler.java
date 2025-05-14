package mx.ipn.pos_support_system_api.entitys.administrator_control.product_change;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProductChangeModelAssembler
implements RepresentationModelAssembler<ProductChange, EntityModel<ProductChange>> {
  @Override
  public EntityModel<ProductChange> toModel(ProductChange productChange) {
    return EntityModel.of(productChange,
        linkTo(methodOn(ProductChangeController.class).one(productChange.getId())).withSelfRel(),
        linkTo(methodOn(ProductChangeController.class).all()).withRel("product-changes"));
  }
}