package spring.multitenancy.tenant;

import java.util.Date;

import spring.multitenancy.entity.AbstractEntity;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant information entity.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class TenantInfo extends AbstractEntity {

	private static final long serialVersionUID = -1539175288355758702L;

	private String tenantId;

	private String tenantName;

	private String accessUrl;

	private Date lastUpdatedDateTime;

	private Date expiryDateTime;

	private boolean active;

	public TenantInfo(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public Date getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public Date getExpiryDateTime() {
		return expiryDateTime;
	}

	public void setExpiryDateTime(Date expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tenantId == null) ? 0 : tenantId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenantInfo other = (TenantInfo) obj;
		if (tenantId == null) {
			if (other.tenantId != null)
				return false;
		} else if (!tenantId.equals(other.tenantId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tenantId;
	}

}