package spring.multitenancy.controller;

import org.springframework.http.HttpHeaders;

/**
 * Spring multi-tenancy implementation.
 * 
 * Abstract API service implementation.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public abstract class AbstractAPIService {

	/**
	 * To get response headers
	 * 
	 * @return
	 */
	protected HttpHeaders getResponseHeaders() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}

	/**
	 * To get response headers
	 * 
	 * @param ex
	 * @return
	 */
	protected HttpHeaders getResponseHeaders(Exception ex) {
		HttpHeaders headers = getResponseHeaders();
		headers.add("Error", ex.getMessage());
		return headers;
	}

}