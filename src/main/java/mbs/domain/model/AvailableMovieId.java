package mbs.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class AvailableMovieId implements Serializable {

    private static final long serialVersionUID = 5378645059157273496L;

    private Integer movieId;
    private LocalDate availableDate;

    public AvailableMovieId(Integer movieId,LocalDate availableDate){
        this.movieId = movieId;
        this.availableDate = availableDate;
    }
    public AvailableMovieId(){

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                +((availableDate==null) ? 0 : availableDate.hashCode());
        result = prime  * result
                + ((movieId==null) ? 0 : movieId.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        AvailableMovieId other = (AvailableMovieId) obj;
        if(availableDate == null) {
            if(other.availableDate != null)
                return false;
        }else if(!availableDate.equals(other.availableDate))
            return false;
        if(movieId==null) {
            if(movieId!=null)
                return false;
        }else if(!movieId.equals(other.movieId))
            return false;
        return true;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }
}
