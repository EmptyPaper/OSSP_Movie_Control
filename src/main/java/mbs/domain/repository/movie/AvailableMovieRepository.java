package mbs.domain.repository.movie;

import mbs.domain.model.AvailableMovie;
import mbs.domain.model.AvailableMovieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AvailableMovieRepository extends JpaRepository<AvailableMovie, AvailableMovieId> {
	List<AvailableMovie> findByAvailableMovieId_AvailableDateOrderByAvailableMovieIdAsc(
            @Param("date")LocalDate availableDate);

	AvailableMovie findByAvailableMovieId_AvailableDateAndAvailableMovieId_MovieId(LocalDate date,Integer movieId);

	AvailableMovie findOne(AvailableMovieId id);
}
