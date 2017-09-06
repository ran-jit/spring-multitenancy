package spring.multitenancy.exception;

import java.util.Date;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant expired exception.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class TenantExpiredException extends Exception {

	private static final long serialVersionUID = -3982705915873467686L;

	public TenantExpiredException(String tenantId, Date expireDate) {
		super("Tenant {" + tenantId + "} expired on " + expireDate + ". " + ExceptionConstants.CONTACT_ADMINISTRATOR_MSG);
	}

}