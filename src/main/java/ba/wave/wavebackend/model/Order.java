package ba.wave.wavebackend.model;

import ba.wave.wavebackend.model.user.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Talent talent;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    private NumberOfWordsEnum numberOfWords;

}
