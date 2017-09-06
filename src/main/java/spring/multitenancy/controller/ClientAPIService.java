package spring.multitenancy.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.multitenancy.entity.ClientInfo;
import spring.multitenancy.repositories.ClientRepository;

/**
 * Spring multi-tenancy implementation.
 * 
 * Client test service implementation.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
@Controller
public class ClientAPIService extends AbstractAPIService {

	@Autowired
	private ClientRepository repository;

	/**
	 * REST API to get client details.
	 * 
	 * Success Response : {"eid":1,"clientName":"TENANT -1","createdDateTime":"2017-09-06","lockingVersion":0}
	 * Error Response : error stack trace
	 * 
	 * @return
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "tenant")
	public ResponseEntity<ClientInfo> getClientDetails() {
		try {
			ClientInfo clientInfo = repository.getClientDetails();
			return new ResponseEntity<ClientInfo>(clientInfo, HttpStatus.OK);
		} catch (Exception ex) {
			HttpHeaders headers = getResponseHeaders(ex);
			return new ResponseEntity<ClientInfo>(headers, HttpStatus.BAD_REQUEST);
		}
	}

}