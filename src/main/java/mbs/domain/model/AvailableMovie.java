package mbs.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AvailableMovie implements Serializable {

    private static final long serialVersionUID = -169775376744903479L;

    @EmbeddedId
    private AvailableMovieId availableMovieId;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @MapsId("movieId")
    private Movie movie;
    private int seats;

    public AvailableMovie(AvailableMovieId availableMovieId){
        this.availableMovieId = availableMovieId;
    }

    public AvailableMovie(){

    }

    public AvailableMovieId getAvailableMovieId() {
        return availableMovieId;
    }

    public void setAvailableMovieId(AvailableMovieId availableMovieId) {
        this.availableMovieId = availableMovieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
