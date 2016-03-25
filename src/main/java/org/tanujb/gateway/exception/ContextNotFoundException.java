package org.tanujb.gateway.exception;

/**
 * This exception is thrown when context is not found for incoming request
 * 
 * @author tbhakre
 *
 */
public class ContextNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContextNotFoundException() {
		super();
	}

	public ContextNotFoundException(String message) {
		super(message);
	}

}
