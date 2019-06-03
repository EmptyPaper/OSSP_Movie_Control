package mbs.domain.service.booking;

import mbs.domain.model.*;
import mbs.domain.repository.booking.BookingRepository;
import mbs.domain.repository.movie.AvailableMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BookingService {
    @Autowired
    AvailableMovieRepository availableMovieRepository;
    @Autowired
    BookingRepository bookingRepository;


    public List<Booking> findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(AvailableMovieId availableMovieId){
        return bookingRepository.findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(availableMovieId);
    }

    public Booking book(Booking booking){
        AvailableMovieId availableMovieId = booking.getAvailableMovie().getAvailableMovieId();
        AvailableMovie availableMovie = availableMovieRepository.findOne(availableMovieId);
        availableMovie.setSeats(availableMovie.getSeats()-1);
        if(availableMovie == null)
            throw new UnavailableBookingException("선택한 날짜와 영화를 예매 할 수 없습니다.");
        else if(availableMovie.getSeats()<0)
            throw new UnavailableBookingException("해당 영화의 예매가능한 빈 좌석이 없습니다.");

        boolean overlap = bookingRepository.findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(availableMovieId)
                .stream().anyMatch(x -> x.overlap(booking));
//        if(overlap)
//            throw new AlreadyBookedException("입력한 시간대에 이미 예매된 건이 있습니다.");
        System.out.println("##"+availableMovie.getSeats());
        bookingRepository.save(booking);
        return booking;
    }

    public void cancel(Integer bookingId, User requestUser){
        Booking booking = bookingRepository.findOne(bookingId);
        AvailableMovieId availableMovieId = booking.getAvailableMovie().getAvailableMovieId();
        AvailableMovie availableMovie = availableMovieRepository.findOne(availableMovieId);
        if(RoleName.ADMIN != requestUser.getRoleName()
            && !Objects.equals(booking.getUser().getUserId(),requestUser.getUserId())){
            throw new UnavailableBookingException("예매를 취소할 수 없습니다.");
        }
        availableMovie.setSeats(availableMovie.getSeats()+1);
        bookingRepository.delete(booking);
    }

}