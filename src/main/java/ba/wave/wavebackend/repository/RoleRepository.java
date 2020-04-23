package ba.wave.wavebackend.repository;

import ba.wave.wavebackend.model.user.Role;
import ba.wave.wavebackend.model.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsRoleByName(@Param("name") RoleName name);
    Role findByName(@Param("name") RoleName name);

    @RestResource(exported = false)
    Optional<Role> findById(Long id);
}
