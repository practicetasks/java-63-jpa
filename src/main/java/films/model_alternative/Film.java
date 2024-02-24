package films.model_alternative;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "release_year")
    private Integer releaseYear;

    @OneToMany(mappedBy = "film")
    private List<FilmGenre> filmGenres;
}
