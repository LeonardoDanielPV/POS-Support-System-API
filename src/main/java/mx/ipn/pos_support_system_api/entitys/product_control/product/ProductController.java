package mx.ipn.pos_support_system_api.entitys.product_control.product;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.ipn.pos_support_system_api.entitys
    .administrator_control.product_change.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(path="/api")
public class ProductController {
  private final ProductRepository repository;
  private final ProductModelAssembler assembler;

  private final ProductChangeRepository productChangeRepository;

  public ProductController(ProductRepository repository,
                           ProductModelAssembler assembler,
                           ProductChangeRepository productChangeRepository) {
    this.repository = repository;
    this.assembler = assembler;
    this.productChangeRepository = productChangeRepository;
  }

  @GetMapping("/products")
  CollectionModel<EntityModel<Product>> all() {
    List<Product> products = repository.findAll();

    List<EntityModel<Product>> product_models = products.stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(product_models,
        linkTo(methodOn(ProductController.class).all()).withSelfRel());
  }

  @PostMapping("/products")
  ResponseEntity<?> newProduct(@RequestBody Product newProduct) {
    Product product = repository.save(newProduct);

    EntityModel<Product> entityModel = assembler.toModel(product);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/products/{bar_code}")
  EntityModel<Product> one(@PathVariable String bar_code) {
    Product product = repository.findById(bar_code)
        .orElseThrow(() -> new ProductNotFoundException(bar_code));

    return assembler.toModel(product);
  }

  @PutMapping("/products/{bar_code}")
  ResponseEntity<?> replaceProduct(@RequestBody Product newProduct,
                                   @PathVariable String bar_code,
                                   @RequestParam String user_id) {
    Optional<Product> product_optional = repository.findById(bar_code);

    Product updateProduct = null;
    if (product_optional.isEmpty()) {
      newProduct.setBar_code(bar_code);
      updateProduct = repository.save(newProduct);
    } else {
      Product product = product_optional.get();

      ProductChange productChange = new ProductChange(
          product.getBar_code(),
          product.getName(),
          product.getPrice(),
          product.getDescription(),
          product.getStock(),
          new Date(System.currentTimeMillis()),
          user_id);
      productChangeRepository.save(productChange);

      product.setName(newProduct.getName());
      product.setPrice(newProduct.getPrice());
      product.setDescription(newProduct.getDescription());
      product.setStock(newProduct.getStock());
      product.setDepartment_id(newProduct.getDepartment_id());
      product.setType_id(newProduct.getType_id());

      updateProduct = repository.save(product);
    }

    EntityModel<Product> entityModel = assembler.toModel(updateProduct);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/products/{bar_code}")
  ResponseEntity<?> deleteProduct(@PathVariable String bar_code,
                                  @RequestParam String user_id) {
    Optional<Product> product_optional = repository.findById(bar_code);

    if (!product_optional.isEmpty()) {
      Product product = product_optional.get();

      ProductChange productChange = new ProductChange(
          product.getBar_code(),
          product.getName(),
          product.getPrice(),
          product.getDescription(),
          product.getStock(),
          new Date(System.currentTimeMillis()),
          user_id);
          
      productChangeRepository.save(productChange);
    }

    repository.deleteById(bar_code);

    return ResponseEntity.noContent().build();
  }
}
