package mbs.domain.service.booking;

public class UnavailableBookingException extends RuntimeException{

    private static final long serialVersionUID = -2830701128234965600L;

    public UnavailableBookingException(String message){
        super(message);
    }
}