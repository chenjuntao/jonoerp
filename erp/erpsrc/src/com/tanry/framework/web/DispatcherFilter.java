package com.tanry.framework.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class DispatcherFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String requestURI = request.getRequestURI();
		String newURI = requestURI.replaceFirst("/.*/m/", "/");

		Map<String, String[]> extraParams = new TreeMap<String, String[]>();
		extraParams.put("fromMobile", new String[] { "true" });
		HttpServletRequest wrappedRequest = new PrettyFacesWrappedRequest(
				request, extraParams);
		request.getRequestDispatcher(newURI).forward(wrappedRequest, res);
	}

	@Override
	public void destroy() {

	}
}

class PrettyFacesWrappedRequest extends HttpServletRequestWrapper {
	private final Map<String, String[]> modifiableParameters;
	private Map<String, String[]> allParameters = null;

	/**
	 * Create a new request wrapper that will merge additional parameters into
	 * the request object without prematurely reading parameters from the
	 * original request.
	 * 
	 * @param request
	 * @param additionalParams
	 */
	public PrettyFacesWrappedRequest(final HttpServletRequest request,
			final Map<String, String[]> additionalParams) {
		super(request);
		modifiableParameters = new TreeMap<String, String[]>();
		modifiableParameters.putAll(additionalParams);
	}

	@Override
	public String getParameter(final String name) {
		String[] strings = getParameterMap().get(name);
		if (strings != null) {
			return strings[0];
		}
		return super.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		if (allParameters == null) {
			allParameters = new TreeMap<String, String[]>();
			allParameters.putAll(super.getParameterMap());
			allParameters.putAll(modifiableParameters);
		}
		// Return an unmodifiable collection because we need to uphold the
		// interface contract.
		return Collections.unmodifiableMap(allParameters);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(getParameterMap().keySet());
	}

	@Override
	public String[] getParameterValues(final String name) {
		return getParameterMap().get(name);
	}
}