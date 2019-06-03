package mbs.app.booking;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

public class BookingForm implements Serializable {

    private static final long serialVersionUID = -8714110067947825887L;

    @NotNull(message = "필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy/M/d")
    private LocalTime bookingDate;
}
