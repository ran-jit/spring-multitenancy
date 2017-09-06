package spring.multitenancy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Spring multi-tenancy implementation.
 * 
 * Abstract entity.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -8685991185758523030L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "eid", unique = true, nullable = false)
	private long eid;

	@Version
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locking_version")
	private long lockingVersion;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date_time", nullable = false, length = 10)
	private Date createdDateTime;

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public long getLockingVersion() {
		return lockingVersion;
	}

	public void setLockingVersion(long lockingVersion) {
		this.lockingVersion = lockingVersion;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return "Entity [eid=" + eid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eid ^ (eid >>> 32));
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
		AbstractEntity other = (AbstractEntity) obj;
		if (eid != other.eid)
			return false;
		return true;
	}

}