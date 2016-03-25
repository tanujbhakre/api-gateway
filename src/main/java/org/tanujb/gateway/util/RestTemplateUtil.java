package org.tanujb.gateway.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

/**
 * This class contains Rest template related utility methods
 * 
 * @author tbhakre
 *
 */
public class RestTemplateUtil {
	/**
	 * This method converts the HTTP Header information to header map and skips
	 * the keys in skip list
	 * 
	 * @param headers
	 *            Header which is to be copied to
	 * @return Map containing the header details
	 */
	public static Map<String, String> setHeadersInfo(HttpHeaders headers,
			Set<String> skipList) {

		Map<String, String> responseHeader = new HashMap<String, String>();

		for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
			if (!skipList.contains(entry.getKey().toLowerCase())) {
				responseHeader.put(entry.getKey(),
						StringUtils.join(entry.getValue(), ','));
			}
		}

		return responseHeader;
	}

	/**
	 * This method copies the value in passed map to HTTP header and skips the
	 * keys present in skip list
	 * 
	 * @param headerMap
	 * @param headers
	 * @param skipList
	 */
	public static void setHeadersInfo(Map<String, String> headerMap,
			HttpHeaders headers, Set<String> skipList) {

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			if (!skipList.contains(entry.getKey().toLowerCase())) {
				headers.add(entry.getKey(), entry.getValue());
			}
		}
	}
}
