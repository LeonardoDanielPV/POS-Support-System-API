package mx.ipn.pos_support_system_api.entitys.general.user;

import java.util.List;
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
public class UserController {
  private final UserRepository repository;
  private final UserModelAssembler assembler;

  UserController(UserRepository repository, UserModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/users")
  CollectionModel<EntityModel<User>> all() {
    List<EntityModel<User>> users = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(users,
        linkTo(methodOn(UserController.class).all()).withSelfRel());
  }

  @PostMapping("/users")
  ResponseEntity<?> newUser(@RequestBody User newUser) {
    EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }
  
  @GetMapping("/users/{id}")
  EntityModel<User> one(@PathVariable String id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(user);
  }

  @PutMapping("/users/{id}")
  ResponseEntity<?> replaceUser(@RequestBody User newUser,
                                @PathVariable String id) {
    User updateUser = repository.findById(id)
        .map(user -> {
          user.setFirst_name(newUser.getFirst_name());
          user.setLast_name(newUser.getLast_name());
          user.setUser_name(newUser.getUser_name());
          user.setPassword(newUser.getPassword());
          user.setType(newUser.getType());
          return repository.save(user);
        })
        .orElseGet(() -> {
          newUser.setId(id);
          return repository.save(newUser);
        });
    
    EntityModel<User> entityModel = assembler.toModel(updateUser);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/users/{id}")
  ResponseEntity<?> deleteUser(@PathVariable String id) {
    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
