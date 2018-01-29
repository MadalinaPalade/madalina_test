package pack.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This customer is not found in the system")
public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomException() {
	}

	public CustomException(String message) {
		super(message);
	}

}
