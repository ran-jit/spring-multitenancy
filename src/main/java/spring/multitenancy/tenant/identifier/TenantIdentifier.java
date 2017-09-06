package spring.multitenancy.tenant.identifier;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import spring.multitenancy.exception.TenantNotExistsException;
import spring.multitenancy.properties.ApplicationProperties;
import spring.multitenancy.tenant.TenantInfo;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant identifier implementation.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public abstract class TenantIdentifier {

	private static Log log = LogFactory.getLog(TenantIdentifier.class);

	private static TenantIdentifier identifier;

	public static void init() {
		try {
			String tenantIdentifierClass = (String) ApplicationProperties.get("tenant.identifier.class");
			identifier = (TenantIdentifier) Class.forName(tenantIdentifierClass).newInstance();
		} catch (Exception ex) {
			log.error("Error while initializing the tenant identifier..", ex);
		}
	}

	public static TenantInfo getTenantInfo(HttpServletRequest request) throws TenantNotExistsException {
		return identifier.identifyTenant(request);
	}

	/**
	 * To identify tenant from request
	 * 
	 * @param request
	 * @return
	 * @throws TenantNotExistsException
	 */
	protected abstract TenantInfo identifyTenant(HttpServletRequest request) throws TenantNotExistsException;

}