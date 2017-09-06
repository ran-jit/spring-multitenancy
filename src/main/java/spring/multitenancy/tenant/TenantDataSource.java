package spring.multitenancy.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant data sources for tenant wise transaction management.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class TenantDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return TenantContext.getCurrentTenant();
	}

}