package ba.wave.wavebackend.repository;

import ba.wave.wavebackend.model.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TalentRepository extends JpaRepository<Talent, Long> {
    @Override
    @Query("SELECT t from Talent t left join fetch t.intonations left join fetch t.languagesSpoken left join fetch t" +
            ".voiceSamples left join fetch t.voiceTypes")
    List<Talent> findAll();

    @Override
    @Query("SELECT t from Talent t where t.id = ?1")
    Optional<Talent> findById(Long aLong);

    @Query(nativeQuery = true, value =
            "SELECT DISTINCT u.* FROM user u\n" +
                    "JOIN user_languages_spoken ls\n" +
                    "ON u.id = ls.talents_id\n" +
                    "WHERE ls.languages_spoken_id IN (?1);")
    List<Talent> findByLanguageIds(@Param("languages") List<Integer> ids);


    @Query(nativeQuery = true, value =
            "SELECT DISTINCT u.* FROM user u\n" +
                    "JOIN user_voice_types uv\n" +
                    "ON u.id = uv.talents_id\n" +
                    "WHERE uv.voice_types_id IN (?1);")
    List<Talent> findByVoiceTypesIds(@Param("voiceTypes") List<Integer> ids);

}
