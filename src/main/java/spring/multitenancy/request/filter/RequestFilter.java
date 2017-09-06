package spring.multitenancy.request.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.multitenancy.exception.TenantExpiredException;
import spring.multitenancy.tenant.TenantContext;
import spring.multitenancy.tenant.TenantInfo;
import spring.multitenancy.tenant.identifier.TenantIdentifier;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant first level filter.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
@Component
@Order(1)
public class RequestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest request = (HttpServletRequest) req;

			TenantInfo tenantInfo = TenantIdentifier.getTenantInfo(request);
			if (!tenantInfo.isActive()) {
				throw new TenantExpiredException(tenantInfo.getTenantId(), tenantInfo.getExpiryDateTime());
			}

			TenantContext.setCurrentTenant(tenantInfo);
			chain.doFilter(req, res);

		} catch (Exception ex) {
			res.getWriter().write(convertObjectToJson(ex.getMessage()));
		} finally {
			TenantContext.removeCurrentTenant();
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		TenantIdentifier.init();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * method to convert object to json
	 * 
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	private String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}