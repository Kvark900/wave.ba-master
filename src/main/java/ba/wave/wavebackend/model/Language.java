package ba.wave.wavebackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = "talents")
@Data
@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "languagesSpoken")
    private Set<Talent> talents;
}
