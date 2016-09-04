package data.com.prism.bank.persister;

import com.google.gson.reflect.TypeToken;

public interface FilePersistListener {

	/**
	 * 
	 */
	public void clear();
	
	/**
	 * 
	 */
	public FilePersistListener config(String topic,String path);
	
	/**
	 */
	public boolean isStored(String key);
	
	/**
	 * 
	 */
	
	public boolean remove(String key);
	
	/**
	 * 
	 */
	public void store(String topic,Object key,Object value,short type) ; 
	
	public Object retrieve(String topic,Object key,short type,TypeToken<?> typeToken) ;
}
