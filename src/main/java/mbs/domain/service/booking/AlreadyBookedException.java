package mbs.domain.service.booking;

public class AlreadyBookedException extends RuntimeException{

    private static final long serialVersionUID = -6546625257946747737L;

    public AlreadyBookedException(String message){
        super(message);
    }
}