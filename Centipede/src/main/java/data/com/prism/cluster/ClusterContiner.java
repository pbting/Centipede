package data.com.prism.cluster;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * <pre>
 * 	集群信息的封装器
 * </pre>
 */
public class ClusterContiner {

	private String role ;
	
	private String masterUrl;
	
	private Set<String> slavesUrl ;
	
	public ClusterContiner() {
	}
	
	public ClusterContiner(String masterUrl, Set<String> slavesUrl,String role) {
		super();
		this.masterUrl = masterUrl;
		this.slavesUrl = slavesUrl;
		this.role = role ;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMasterUrl() {
		return masterUrl;
	}

	public void setMasterUrl(String masterUrl) {
		this.masterUrl = masterUrl;
	}

	public Set<String> getSalvesUrl() {
		return Collections.unmodifiableSet(slavesUrl);
	}

	public void addSalveUrl(String salveUrl) {
		if(this.slavesUrl == null){
			synchronized (ClusterContiner.class) {
				if(this.slavesUrl==null){
					this.slavesUrl = Collections.synchronizedSet(new TreeSet<String>());
				}
			}
		}
		this.slavesUrl.add(salveUrl);
	}
}
