package spring.multitenancy.tenant;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant context to get the tenant informations in request level.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class TenantContext {

	private static ThreadLocal<TenantInfo> tenants = new ThreadLocal<>();

	/**
	 * To set current tenant info
	 * 
	 * @param tenant
	 */
	public static void setCurrentTenant(TenantInfo tenant) {
		tenants.set(tenant);
	}

	/**
	 * To get current tenant info
	 * 
	 * @return
	 */
	public static TenantInfo getCurrentTenant() {
		return tenants.get();
	}

	/**
	 * To remove the current tenant
	 */
	public static void removeCurrentTenant() {
		if (tenants.get() != null)
			tenants.remove();
	}
}