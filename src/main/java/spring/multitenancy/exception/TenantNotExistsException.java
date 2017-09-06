package spring.multitenancy.exception;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant not exists exception.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class TenantNotExistsException extends Exception {

	private static final long serialVersionUID = -910621592623013609L;

	public TenantNotExistsException(String tenantId) {
		super("Invalid tenant id: {" + tenantId + "}.. " + ExceptionConstants.CONTACT_ADMINISTRATOR_MSG);
	}

}