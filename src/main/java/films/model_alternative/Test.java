package films.model_alternative;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManager manager = Persistence.createEntityManagerFactory("default").createEntityManager();

        Film film = manager.find(Film.class, 1L);

        System.out.println(film.getName());
        System.out.println(film.getReleaseYear());
        for (FilmGenre filmGenre : film.getFilmGenres()) {
            System.out.println(filmGenre.getGenre().getName());
        }
    }
}
