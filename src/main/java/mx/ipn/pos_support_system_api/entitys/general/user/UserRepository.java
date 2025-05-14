package mx.ipn.pos_support_system_api.entitys.general.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  
}
