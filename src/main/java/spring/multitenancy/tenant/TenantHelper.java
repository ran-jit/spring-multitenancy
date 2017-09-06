package spring.multitenancy.tenant;

import java.util.List;
import java.util.Map;
import java.util.Set;

import spring.multitenancy.data.cache.DataCache;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant Helper implementation to get tenant details.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class TenantHelper {

	/**
	 * To get tenant info
	 * 
	 * @param tenantId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static TenantInfo getTenantInfo(String tenantId) {

		Map<TenantInfo, List<TenantProperty>> tenantInfos = (Map<TenantInfo, List<TenantProperty>>) DataCache
				.getInstance(TenantConstants.TENANT_COMMON).get(TenantConstants.TENANTS_INFO);

		Set<TenantInfo> tenantInfo = tenantInfos.keySet();
		for (TenantInfo tenant : tenantInfo) {
			if (tenant.getTenantId().equals(tenantId)) {
				return tenant;
			}
		}
		return null;
	}

	/**
	 * To get tenant properties
	 * 
	 * @param tenantInfo
	 * @param propertyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static TenantProperty getTenantProperty(TenantInfo tenantInfo, String propertyName) {

		List<TenantProperty> tenantProperties = ((Map<TenantInfo, List<TenantProperty>>) DataCache
				.getInstance(TenantConstants.TENANT_COMMON).get(TenantConstants.TENANTS_INFO)).get(tenantInfo);

		for (TenantProperty tenantProperty : tenantProperties) {
			if (tenantProperty.getPropertyName().equals(propertyName)) {
				return tenantProperty;
			}
		}
		return null;
	}
}