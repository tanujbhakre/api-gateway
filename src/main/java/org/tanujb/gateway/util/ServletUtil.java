package org.tanujb.gateway.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.tanujb.gateway.exception.ApplicationRuntimeException;

/**
 * This class contains the Servlet related utility methods
 * 
 * @author tbhakre
 *
 */
public class ServletUtil {

	/**
	 * This constant represents the request parameter separator for GET requests
	 */
	private static final String GET_PARAM_SEPARATOR = "?";

	/**
	 * This constant represents the delimiter user for reading request body
	 */
	public static final String REQUEST_BODY_DELIMITER = "\\A";

	/**
	 * This method returns the request body for POST and PUT HTTP requests
	 * otherwise returns null
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestBody(HttpServletRequest request) {
		String requestBody = null;

		Scanner scanner = null;
		try {
			scanner = new Scanner(request.getInputStream(),
					StandardCharsets.UTF_8.name())
					.useDelimiter(REQUEST_BODY_DELIMITER);
			requestBody = scanner.hasNext() ? scanner.next() : "";

		} catch (IOException e) {
			ApplicationRuntimeException are = new ApplicationRuntimeException(
					"Error occured while reading the request body", e);
			throw are;
		} finally {
			scanner.close();
		}

		return requestBody;
	}

	/**
	 * This method returns the headers details passed request
	 * 
	 * @param request
	 *            Request whose headers are to be retrieved
	 * @return Map containing the header details
	 */
	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	/**
	 * This method returns the URL related with request
	 * 
	 * @param request
	 * @return
	 */
	public static String getURL(HttpServletRequest request) {
		return request.getServletPath()
				+ (request.getQueryString() != null ? GET_PARAM_SEPARATOR
						+ ServletUtil.getQueryParamsWithPlaceHolders(request)
						: "");
	}

	/**
	 * Creating URL with place holders
	 * 
	 * @param request
	 * @return
	 */
	public static String getQueryParamsWithPlaceHolders(
			HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		Map<String, String[]> queryParamMap = request.getParameterMap();
		if (queryParamMap != null) {
			for (Map.Entry<String, String[]> entry : queryParamMap.entrySet()) {
				String key = entry.getKey();
				if (sb.length() > 0)
					sb.append("&");
				sb.append(key).append("=").append("{").append(key).append("}");
			}
		}
		return sb.toString();
	}

	/**
	 * Returns the placeholder values for incoming request
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getPlaceHolderDetails(
			HttpServletRequest request) {

		Map<String, String> placeHolders = new HashMap<String, String>();
		Map<String, String[]> queryParamMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : queryParamMap.entrySet()) {
			String key = entry.getKey();
			String[] valArray = entry.getValue();
			String value = StringUtils.join(valArray, ',');
			placeHolders.put(key, value);
		}
		return placeHolders;
	}

}
