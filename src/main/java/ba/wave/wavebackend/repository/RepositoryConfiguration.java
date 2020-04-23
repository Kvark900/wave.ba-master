package ba.wave.wavebackend.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration{

    public RepositoryConfiguration(){
        super();
    }

    @Bean
    UserEventHandler authorEventHandler() {
        return new UserEventHandler();
    }

}
