package org.tanujb.gateway.util;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GatewayUtil {
	/**
	 * This method matches provided string with list of expressions
	 * 
	 * @param string
	 *            - The string which is to be matched
	 * @param expressions
	 *            - List of expressions against which string is to be compared
	 * @return Returns true if provided string matches any of the provided
	 *         expressions
	 */
	public static boolean matchesAnyExpression(String string,
			Set<String> expressions) {
		boolean matches = false;
		if (expressions != null) {
			// Iterating over all the expressions
			for (String expression : expressions) {
				// Matching pattern for given string
				Pattern pattern = Pattern.compile(expression);
				Matcher matcher = pattern.matcher(string);
				if (matcher.matches()) {
					matches = true;
					break;
				}
			}
		}
		return matches;
	}
}
