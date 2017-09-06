package spring.multitenancy.tenant;

import spring.multitenancy.entity.AbstractEntity;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant property entity.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
class TenantProperty extends AbstractEntity {

	private static final long serialVersionUID = 9128212088174351679L;

	private TenantInfo tenantInfo;

	private String propertyName;

	private String propertyValue;

	public TenantInfo getTenantInfo() {
		return tenantInfo;
	}

	public void setTenantInfo(TenantInfo tenantInfo) {
		this.tenantInfo = tenantInfo;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		result = prime * result + ((tenantInfo == null) ? 0 : tenantInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenantProperty other = (TenantProperty) obj;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		if (tenantInfo == null) {
			if (other.tenantInfo != null)
				return false;
		} else if (!tenantInfo.equals(other.tenantInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TenantProperty [tenantInfo=" + tenantInfo + ", " + propertyName + " = " + propertyValue + "]";
	}

}