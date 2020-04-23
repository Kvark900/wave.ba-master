package ba.wave.wavebackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class VoiceSample {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private Talent user;

    @URL
    private String location;

    @ManyToOne
    private VoiceSampleType voiceSampleType;

    @ManyToOne
    private Intonation intonation;

    @ManyToOne
    private VoiceType voiceType;

    @ManyToOne
    private Language language;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date uploadDate;

}
