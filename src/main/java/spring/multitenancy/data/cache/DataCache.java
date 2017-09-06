package spring.multitenancy.data.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring multi-tenancy implementation.
 * 
 * Data cache.
 * 
 * @author Ranjith Manickam
 * @since 1.0
 */
public class DataCache {

	private static Map<String, DataCache> instance = new HashMap<>();

	private Map<String, Object> cache;

	private DataCache() {
		cache = new ConcurrentHashMap<>();
	}

	/**
	 * method to get data-cache instance
	 * 
	 * @param tenantId
	 * @return
	 */
	public synchronized static DataCache getInstance(String tenantId) {

		if (!instance.containsKey(tenantId)) {
			DataCache dataCache = new DataCache();
			instance.put(tenantId, dataCache);
		}
		return instance.get(tenantId);
	}

	/**
	 * method to add value<K,V> to data-cache
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		cache.put(key, value);
	}

	/**
	 * method to get value from data-cache
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return cache.get(key);
	}
}