package com.lpmas.framework.web;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class DomainKit {
	private final static String topDomainRegex = "(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)";
	private static Pattern topDomainPattern = Pattern.compile(topDomainRegex, Pattern.CASE_INSENSITIVE);

	public static String getTopDomain(String url) {
		return null;
	}

	public static String getTopDomain(HttpServletRequest request) {
		return null;
	}
}
