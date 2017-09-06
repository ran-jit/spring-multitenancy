package spring.multitenancy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Spring multi-tenancy implementation.
 * 
 * Client details entity.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
@Entity
@Table(name = "client_info")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientInfo extends AbstractEntity {

	private static final long serialVersionUID = -6255947023957737560L;

	@Column(name = "client_name", unique = true, nullable = false)
	private String clientName;

	public ClientInfo() {
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@JsonCreator
	public ClientInfo(@JsonProperty("eid") long eid, @JsonProperty("clientName") String clientName,
			@JsonProperty("createdDateTime") Date createdDateTime) {
		setEid(eid);
		setClientName(clientName);
		setCreatedDateTime(createdDateTime);
	}
}