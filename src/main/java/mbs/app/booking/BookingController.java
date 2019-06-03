package mbs.app.booking;

import mbs.domain.model.*;
import mbs.domain.service.booking.AlreadyBookedException;
import mbs.domain.service.booking.BookingService;
import mbs.domain.service.booking.UnavailableBookingException;
import mbs.domain.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("booking/{date}/{movieId}")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    MovieService movieService;

    private User dummyUser(){
        User user = new User();
        user.setUserId("Je Sanghyun");
        user.setFirstName("종빈");
        user.setLastName("이");
        user.setRoleName(RoleName.USER);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    String bookingForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
                       @PathVariable("movieId") Integer movieId, Model model){
        AvailableMovieId availableMovieId = new AvailableMovieId(movieId,date);
        AvailableMovie availableMovie = movieService.findOne(availableMovieId);
        List<Booking> bookings = bookingService.findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(availableMovieId);
        model.addAttribute("availableMovie",availableMovie);
        model.addAttribute("movie",movieService.findByMovieId(movieId));
        model.addAttribute("date", date);
        model.addAttribute("bookings", bookings);
        model.addAttribute("user", dummyUser());
        return "booking/bookingForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    String book(@Validated BookingForm form, BindingResult bindingResult,
                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
                   @PathVariable("movieId") Integer movieId, Model model){
        System.out.println("##booking page");
//        if(bindingResult.hasErrors()){
//            System.out.println("##BINDING ERROR");
//            return bookingForm(date,movieId,model);
//        }
        AvailableMovie availableMovie = new AvailableMovie(new AvailableMovieId(movieId,date));
        Booking booking = new Booking();
        booking.setBookedDate(date);
        booking.setAvailableMovie(availableMovie);
        booking.setUser(dummyUser());
        try{
            System.out.println("##BOKKK COMP:LETE");
            bookingService.book(booking);
        }
        catch (UnavailableBookingException | AlreadyBookedException e){
            System.out.println("##BOKKK FIAIALLL");
            model.addAttribute("error", e.getMessage());
            return bookingForm(date,movieId,model);
        }
        return "redirect:/booking/{date}/{movieId}";

    }
    @RequestMapping(method = RequestMethod.POST,params = "cancel")
    String cancel(@RequestParam("bookingId") Integer bookingId,
                  @PathVariable("movieId") Integer movieId,
                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
                  Model model){
        User user = dummyUser();

        try {
            bookingService.cancel(bookingId,user);
        } catch (UnavailableBookingException e){
            model.addAttribute("error", e.getMessage());
            return bookingForm(date,movieId,model);
        }
        return "redirect:/booking/{date}/{movieId}";
    }



}