package data.com.prism.cluster;

import data.com.prism.mgr.ConfigMgr;

public class ClusterMgr {

	private ClusterMgr() {
	}

	private static ClusterContiner CLUSTER_CONTINER = null;

	public static void init() {
		CLUSTER_CONTINER = new ClusterContiner();
		String master = ConfigMgr.getMaster();
		CLUSTER_CONTINER.setMasterUrl(master);
		CLUSTER_CONTINER.addSalveUrl(ConfigMgr.getSlave());
		CLUSTER_CONTINER.setRole(ConfigMgr.getRole());
	}

	public static ClusterContiner getClusterContiner() {
		return CLUSTER_CONTINER;
	}
}
