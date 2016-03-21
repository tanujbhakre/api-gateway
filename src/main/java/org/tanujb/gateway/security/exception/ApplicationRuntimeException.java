package org.tanujb.gateway.security.exception;

/**
 * An unchecked exception class for wrapping all runtime Exceptions
 */
public class ApplicationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public ApplicationRuntimeException() {
		super();
	}

	public ApplicationRuntimeException(String message) {
		super(message);
	}

	public ApplicationRuntimeException(Throwable cause) {
		super(cause);
	}

	public ApplicationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
