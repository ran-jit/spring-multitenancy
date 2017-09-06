package spring.multitenancy.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.multitenancy.entity.ClientInfo;

/**
 * Spring multi-tenancy implementation.
 * 
 * Tenant test data repository.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
@Repository
public interface ClientRepository extends CrudRepository<ClientInfo, Long> {

	/**
	 * To get client details
	 * 
	 * @return
	 */
	@Query("SELECT c FROM ClientInfo c")
	ClientInfo getClientDetails();

}