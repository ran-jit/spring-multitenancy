package spring.multitenancy.tenant.identifier;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import spring.multitenancy.exception.TenantNotExistsException;
import spring.multitenancy.properties.ApplicationProperties;
import spring.multitenancy.tenant.TenantHelper;
import spring.multitenancy.tenant.TenantInfo;

/**
 * Spring multi-tenancy implementation.
 * 
 * Attribute tenant identifier implementation to identify tenants based on URL attribute.
 * 
 * ex: https://localhost/api?tenant=ranjith -> tenant id: ranjith
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
class AttributeTenantIdentifier extends TenantIdentifier {

	/** {@inheritDoc} */
	@Override
	public TenantInfo identifyTenant(HttpServletRequest request) throws TenantNotExistsException {

		String param = (String) ApplicationProperties.get("attribute.tenant.identifier.param");
		String tenantId = request.getParameter(param);

		if (StringUtils.isEmpty(tenantId)) {
			tenantId = (String) request.getAttribute(param);
		}

		TenantInfo tenantInfo = TenantHelper.getTenantInfo(tenantId);

		if (tenantInfo == null) {
			throw new TenantNotExistsException(tenantId);
		}

		request.setAttribute(param, tenantId);
		return tenantInfo;
	}

}