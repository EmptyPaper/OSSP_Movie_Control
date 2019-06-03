package mbs.domain.repository.booking;

import mbs.domain.model.AvailableMovieId;
import mbs.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
	 Booking findByBookedDate(Integer bookedId);
	 List<Booking> findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(AvailableMovieId id);
}
