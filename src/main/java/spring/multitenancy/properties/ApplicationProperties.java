package spring.multitenancy.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Spring multi-tenancy implementation.
 * 
 * Application properties reader
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class ApplicationProperties {

	private Log log = LogFactory.getLog(ApplicationProperties.class);

	private Properties properties;

	private ApplicationProperties() {
		init();
	}

	private static class ApplicationPropertiesHelper {
		private static final ApplicationProperties INSTANCE = new ApplicationProperties();
	}

	/**
	 * method to get application properties
	 * 
	 * @return
	 */
	public static Object get(String key) {
		return ApplicationPropertiesHelper.INSTANCE.properties.get(key);
	}

	/**
	 * method to load the application properties
	 * 
	 * @return
	 */
	private Properties init() {
		properties = new Properties();
		try {
			InputStream resourceStream = null;
			try {
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				resourceStream = loader.getResourceAsStream("application.properties");

				properties.load(resourceStream);
			} finally {
				resourceStream.close();
			}
		} catch (IOException ex) {
			log.error("Error while loading task scheduler properties", ex);
		}
		return properties;
	}

}