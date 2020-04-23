package ba.wave.wavebackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WaveBackendApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testPasswordSuccessfulDecoding() {
        String pass = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(pass, "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.");
        assertTrue(matches);
    }


    @Test
    void testPasswordFailDecoding() {
        String pass = "1234561";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(pass, "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.");
        assertFalse(matches);
    }


}
