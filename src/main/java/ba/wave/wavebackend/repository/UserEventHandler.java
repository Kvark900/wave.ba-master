package ba.wave.wavebackend.repository;

import ba.wave.wavebackend.model.Talent;
import ba.wave.wavebackend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Logger;

@RepositoryEventHandler(value = {User.class, Talent.class})
public class UserEventHandler {
    private Logger logger = Logger.getLogger("Class AuthorEventHandler");

    @Autowired
    private PasswordEncoder passwordEncoder;

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(User user) {
        logger.info("Inside User Before Create....");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @HandleAfterCreate
    public void handleAuthorAfterCreate(User user) {
        logger.info("Inside User After Create ....");
    }

    @HandleBeforeDelete
    public void handleAuthorBeforeDelete(User user) {
        logger.info("Inside User Before Delete ....");
    }

    @HandleAfterDelete
    public void handleAuthorAfterDelete(User user) {
        logger.info("Inside User After Delete ....");
    }

}
