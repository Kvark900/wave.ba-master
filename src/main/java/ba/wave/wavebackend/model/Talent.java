package ba.wave.wavebackend.model;

import ba.wave.wavebackend.model.user.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Talent extends User {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Language> languagesSpoken;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<VoiceType> voiceTypes;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Intonation> intonations;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<VoiceSample> voiceSamples;

}
