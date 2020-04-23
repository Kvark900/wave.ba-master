package ba.wave.wavebackend.model.user;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "userWithRoles", types = {User.class})
public interface UserProjection {
    String getFirstName();
    String getLastName();
    List<Role> getRoles();
}
