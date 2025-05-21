package mx.ipn.pos_support_system_api.entities.administrator_control.product_change;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(path="/api")
public class ProductChangeController {
  private final ProductChangeRepository repository;
  private final ProductChangeModelAssembler assembler;

  public ProductChangeController(ProductChangeRepository repository,
                                 ProductChangeModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/product-changes")
  CollectionModel<EntityModel<ProductChange>> all() {
    List<EntityModel<ProductChange>> product_changes = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(product_changes,
        linkTo(methodOn(ProductChangeController.class).all()).withSelfRel());
  }

  @PostMapping("/product-changes")
  ResponseEntity<?> newProductChange(
      @RequestBody ProductChange newProductChange) {
    EntityModel<ProductChange> entityModel = assembler.toModel(
        repository.save(newProductChange));
    
    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/product-changes/{id}")
  EntityModel<ProductChange> one(@PathVariable Integer id) {
    ProductChange productChange = repository.findById(id)
        .orElseThrow(() -> new ProductChangeNotFoundException(id));

    return assembler.toModel(productChange);
  }

  @PutMapping("/product-changes/{id}")
  ResponseEntity<?> replaceProductChange(
      @RequestBody ProductChange newProductChange,
      @PathVariable Integer id) {
    Optional<ProductChange> product_change_optional = repository.findById(id);

    ProductChange updateProductChange = null;
    if (product_change_optional.isEmpty()) {
      updateProductChange = repository.save(newProductChange);
    } else {
      ProductChange product_change = product_change_optional.get();

      product_change.setBar_code(newProductChange.getBar_code());
      product_change.setPrevious_name(newProductChange.getPrevious_name());
      product_change.setPrevious_price(newProductChange.getPrevious_price());
      product_change.setPrevious_description(
          newProductChange.getPrevious_description());
      product_change.setPrevious_stock(newProductChange.getPrevious_stock());
      product_change.setDate(newProductChange.getDate());

      updateProductChange = repository.save(product_change);
    }

    EntityModel<ProductChange> entityModel = assembler.toModel(
        updateProductChange);
    
    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/product-changes/{id}")
  ResponseEntity<?> deleteProductChange(@PathVariable Integer id) {
    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
