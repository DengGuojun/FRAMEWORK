package com.lpmas.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.StringKit;

public class RequestCharsetEncodingFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(RequestCharsetEncodingFilter.class);

	private String defaultEncoding = "";// 设置默认编码
	private boolean forceEncoding = false;// 是否忽略原有的编码

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (forceEncoding || StringKit.isNull(request.getCharacterEncoding())) {
			if (!StringKit.isNull(defaultEncoding)) {
				request.setCharacterEncoding(defaultEncoding);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		defaultEncoding = filterConfig.getInitParameter("defaultEncoding");
		forceEncoding = filterConfig.getInitParameter("forceEncoding").equals("true") ? true : false;
		log.debug("init RequestCharsetEncodingFilter: defaultEncoding = {}, forceEncoding = {}", defaultEncoding,
				forceEncoding);

	}

}
