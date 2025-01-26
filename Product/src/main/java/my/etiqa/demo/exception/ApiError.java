package my.etiqa.demo.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    // Default constructor
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    // Constructor with HttpStatus
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
    }

    // Constructor with HttpStatus and Throwable
    public ApiError(HttpStatus status, Throwable ex) {
        this(status);
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    // Constructor with HttpStatus, message, and Throwable
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(status);
        this.message = message;
        this.debugMessage = ex != null ? ex.getLocalizedMessage() : null;
    }
}
