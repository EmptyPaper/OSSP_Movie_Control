package mbs.domain.repository.movie;

import mbs.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieId(@Param("ID") Integer movieId);
}
