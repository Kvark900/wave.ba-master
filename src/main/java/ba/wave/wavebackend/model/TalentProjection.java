package ba.wave.wavebackend.model;

import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "talentProj", types = {Talent.class})
public interface TalentProjection {
    Long getId();
    String getUsername();
    String getFirstName();
    String getLastName();
    Set<Language> getLanguagesSpoken();
    Set<VoiceType> getVoiceTypes();
    Set<Intonation> getIntonations();
    Set<VoiceSample> getVoiceSamples();
}
