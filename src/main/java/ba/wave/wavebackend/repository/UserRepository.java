package ba.wave.wavebackend.repository;

import ba.wave.wavebackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    @Query("SELECT u.username, u.email from User u where type(u) in (Talent, User)")
    @Override
    List<User> findAll();

    @Query("SELECT u FROM User u JOIN FETCH u.roles where u.username  = ?1")
    User findByUsername(String username);

    boolean existsUsersByEmail(String email);

    User findByEmail(String email);
}
    