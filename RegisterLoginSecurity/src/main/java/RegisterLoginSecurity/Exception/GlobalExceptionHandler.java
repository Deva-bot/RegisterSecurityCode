package RegisterLoginSecurity.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler 
{
	// Handle UserNameAlreadyExistsException
    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> userAlreadyExistsException(UserNameAlreadyExistsException exception) 
    {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(HttpStatus.CONFLICT.value()); // Use 409 Conflict for existing resource
        errorDetails.setStatus(HttpStatus.CONFLICT.name());
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    
 // Handle EmailAlreadyExistsException
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> userAlreadyExistsException(EmailAlreadyExistsException exception) 
    {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(HttpStatus.CONFLICT.value()); // Use 409 Conflict for existing resource
        errorDetails.setStatus(HttpStatus.CONFLICT.name());
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    
 // Handle UserNameNotFoundException
    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<ErrorDetails> theaterNotFountException (UserNameNotFoundException exception) 
    {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(HttpStatus.NOT_FOUND.value()); // Use 409 Conflict for existing resource
        errorDetails.setStatus(HttpStatus.NOT_FOUND.name());
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
   	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
   	    // Extract field errors from the exception
   	    List<String> fieldErrors = exception.getBindingResult()
   	                                        .getFieldErrors()
   	                                        .stream()
   	                                        .map(FieldError::getDefaultMessage)
   	                                        .collect(Collectors.toList());

   	    // Create a response map for error details
   	    Map<String, Object> errorMap = new HashMap<>();
   	    errorMap.put("status", HttpStatus.BAD_REQUEST.name());
   	    errorMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
   	    errorMap.put("timeStamp", LocalDateTime.now());
   	    errorMap.put("errorMessages", fieldErrors); 
   	    
   	    
   	    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
    
    
}
