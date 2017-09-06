package spring.multitenancy.tenant;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant constants
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
interface TenantConstants {

	static final String TENANT_COMMON = "tenant_ranmanic";
	static final String TENANTS_INFO = "tenants_info";

	static final String DATASOURCE_DRIVER_CLASS = "tenant.database.driverClassName";
	static final String DATASOURCE_CONNECTION_URL = "tenant.database.connection.url";
	static final String DATASOURCE_USERNAME = "tenant.database.username";
	static final String DATASOURCE_PASSWORD = "tenant.database.password";

	enum JobGroup {
		TENANT
	}
}