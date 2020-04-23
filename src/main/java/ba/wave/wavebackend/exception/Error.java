package ba.wave.wavebackend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Error {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy. HH:mm a z")
    private Date timestamp;
    private String status;
    private String error;
    private String path;

    public Error(Date timestamp, String error, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
    }

    public Error status(String httpStatus) {
        setStatus(httpStatus);
        return this;
    }

}
