package spring.multitenancy.tenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.multitenancy.data.cache.DataCache;

/**
 * Spring multi-tenancy implementation.
 * 
 * Initializing the tenant data sources.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
@Configuration
public class TenantConfiguration {

	@Autowired
	private DataSourceProperties properties;

	private Log log = LogFactory.getLog(TenantConfiguration.class);

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		try {
			DataSource defaultDataSource = getDefaultDataSource();
			Map<TenantInfo, List<TenantProperty>> tenantInfos = getTenantInfo(defaultDataSource);

			Map<Object, Object> tenantDataSources = new HashMap<>();

			if (tenantInfos != null) {
				for (TenantInfo tenantInfo : tenantInfos.keySet()) {
					if (tenantInfo.isActive()) {

						DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader());
						dataSourceBuilder.driverClassName(TenantHelper.getTenantProperty(tenantInfo, TenantConstants.DATASOURCE_DRIVER_CLASS).getPropertyValue());
						dataSourceBuilder.url(TenantHelper.getTenantProperty(tenantInfo, TenantConstants.DATASOURCE_CONNECTION_URL).getPropertyValue());
						dataSourceBuilder.username(TenantHelper.getTenantProperty(tenantInfo, TenantConstants.DATASOURCE_USERNAME).getPropertyValue());
						dataSourceBuilder.password(TenantHelper.getTenantProperty(tenantInfo, TenantConstants.DATASOURCE_PASSWORD).getPropertyValue());

						tenantDataSources.put(tenantInfo, dataSourceBuilder.build());
					}
				}
			}

			final TenantDataSource dataSource = new TenantDataSource();

			// default data source
			dataSource.setDefaultTargetDataSource(defaultDataSource);

			// tenant data source
			dataSource.setTargetDataSources(tenantDataSources);

			// Call this to finalize the initialization of the data source.
			dataSource.afterPropertiesSet();

			return dataSource;
		} catch (Exception ex) {
			log.error("Error occured while building data sources", ex);
		}
		return null;
	}

	/**
	 * method to get default data source
	 * 
	 * @return
	 */
	private DataSource getDefaultDataSource() {

		DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader());
		dataSourceBuilder.driverClassName(properties.getDriverClassName());
		dataSourceBuilder.url(properties.getUrl());
		dataSourceBuilder.username(properties.getUsername());
		dataSourceBuilder.password(properties.getPassword());

		if (properties.getType() != null) {
			dataSourceBuilder.type(properties.getType());
		}

		return dataSourceBuilder.build();
	}

	/**
	 * method to get tenant info
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	private Map<TenantInfo, List<TenantProperty>> getTenantInfo(DataSource dataSource) throws Exception {
		final String TENANT_INFO = "SELECT tenant_id, tenant_name, access_url, created_date_time, last_updated_date_time, expiry_date_time, active FROM tenant_info";

		Map<TenantInfo, List<TenantProperty>> tenantInfos = new HashMap<>();
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(TENANT_INFO);) {

			if (result != null) {
				while (result.next()) {
					TenantInfo tenantInfo = new TenantInfo(result.getString("tenant_id"));
					tenantInfo.setTenantName(result.getString("tenant_name"));
					tenantInfo.setAccessUrl(result.getString("access_url"));
					tenantInfo.setCreatedDateTime(result.getTimestamp("created_date_time"));
					tenantInfo.setLastUpdatedDateTime(result.getTimestamp("last_updated_date_time"));
					tenantInfo.setExpiryDateTime(result.getTimestamp("expiry_date_time"));
					tenantInfo.setActive(result.getBoolean("active"));

					tenantInfos.put(tenantInfo, null);
				}
			}
		}

		for (TenantInfo tenantInfo : tenantInfos.keySet()) {
			List<TenantProperty> tenantProperties = getTenantProperties(dataSource, tenantInfo);
			tenantInfos.put(tenantInfo, tenantProperties);
		}

		// adding the tenant informations to cache
		DataCache.getInstance(TenantConstants.TENANT_COMMON).put(TenantConstants.TENANTS_INFO, tenantInfos);

		return tenantInfos;
	}

	/**
	 * method to get tenant properties
	 * 
	 * @param dataSource
	 * @param tenantInfo
	 * @return
	 * @throws Exception
	 */
	private List<TenantProperty> getTenantProperties(DataSource dataSource, TenantInfo tenantInfo) throws Exception {
		final String TENANT_PROPERTY = "SELECT TPI.property_name AS PROPERTY_NAME, TPV.property_value AS PROPERTY_VALUE FROM tenant_info TI, tenant_properties_info TPI, tenant_properties_value TPV WHERE TI.active = 1 AND TI.tenant_id = ? AND TI.eid = TPV.tenant_eid AND TPI.eid = TPV.property_eid";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(TENANT_PROPERTY);) {

			statement.setString(1, tenantInfo.getTenantId());
			ResultSet result = statement.executeQuery();

			List<TenantProperty> tenantProperties = null;
			if (result != null) {
				tenantProperties = new ArrayList<>();
				while (result.next()) {
					TenantProperty tenantProperty = new TenantProperty();
					tenantProperty.setPropertyName(result.getString("PROPERTY_NAME"));
					tenantProperty.setPropertyValue(result.getString("PROPERTY_VALUE"));
					tenantProperty.setTenantInfo(tenantInfo);

					tenantProperties.add(tenantProperty);
				}
			}
			result.close();

			return tenantProperties;
		}
	}
}