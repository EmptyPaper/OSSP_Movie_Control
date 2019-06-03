package mbs.domain.service.movie;

import mbs.domain.model.AvailableMovie;
import mbs.domain.model.AvailableMovieId;
import mbs.domain.model.Movie;
import mbs.domain.repository.movie.AvailableMovieRepository;
import mbs.domain.repository.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    AvailableMovieRepository availableMovieRepository;
    @Autowired
    MovieRepository movieRepository;

    public List<AvailableMovie> findByAvailableMovieId_AvailableDateOrderByAvailableMovieIdAsc(LocalDate date) {
        return availableMovieRepository.findByAvailableMovieId_AvailableDateOrderByAvailableMovieIdAsc(date);
    }
    public Movie findByMovieId(Integer Id){
       return movieRepository.findByMovieId(Id);
    }
    public AvailableMovie findByAvailableMovieId_AvailableDateAndAvailableMovieId_MovieId(LocalDate date, Integer movieId){
        return availableMovieRepository.findByAvailableMovieId_AvailableDateAndAvailableMovieId_MovieId(date,movieId);
    }
    public AvailableMovie findOne(AvailableMovieId id){
        return availableMovieRepository.findOne(id);
    }
}