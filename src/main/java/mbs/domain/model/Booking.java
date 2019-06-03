package mbs.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1896457717476458856L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private LocalDate bookedDate;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "available_date"),
            @JoinColumn(name = "movie_id")
    })
    private AvailableMovie availableMovie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }

    public AvailableMovie getAvailableMovie() {
        return availableMovie;
    }

    public void setAvailableMovie(AvailableMovie availableMovie) {
        this.availableMovie = availableMovie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean overlap(Booking target) {
        if (!Objects.equals(availableMovie.getAvailableMovieId(),
                target.availableMovie.getAvailableMovieId())) {
            return false;
        }
        if (bookedDate.equals(target.bookedDate)) {
            return true;
        }
        return false;
    }
}
