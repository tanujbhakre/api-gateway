package org.tanujb.gateway.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.tanujb.gateway.exception.ApplicationRuntimeException;

/**
 * Controller advice for handling exceptions
 * 
 * @author tbhakre
 *
 */
@ControllerAdvice
public class GatewayExceptionHandler {

	/**
	 * Capturing rest template exceptions and sending response as is.
	 * 
	 * @param response
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseBody
	public String httpExceptionHandler(HttpServletResponse response,
			HttpClientErrorException ex) {
		ex.printStackTrace();
		response.setStatus(ex.getStatusCode().value());
		return ex.getResponseBodyAsString();
	}

	/**
	 * Capturing runtime exception and responding with error
	 * 
	 * @param response
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ApplicationRuntimeException.class)
	@ResponseBody
	public String applicationExceptionHandler(HttpServletResponse response,
			ApplicationRuntimeException ex) {
		ex.printStackTrace();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ex.getMessage();
	}
}
