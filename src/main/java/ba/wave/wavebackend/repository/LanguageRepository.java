package ba.wave.wavebackend.repository;

import ba.wave.wavebackend.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
