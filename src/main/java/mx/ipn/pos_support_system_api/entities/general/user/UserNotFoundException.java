package mx.ipn.pos_support_system_api.entities.general.user;

public class UserNotFoundException extends RuntimeException {
  UserNotFoundException(String id) {
    super("Could not find user " + id);
  }
}
