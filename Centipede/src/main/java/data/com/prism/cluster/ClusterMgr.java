package data.com.prism.cluster;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import data.com.prism.mgr.ConfigMgr;

public class ClusterMgr {

	private ClusterMgr() {
	}

	private static ClusterContiner CLUSTER_CONTINER = null;

	public static void init() {
		CLUSTER_CONTINER = new ClusterContiner();
		String master = ConfigMgr.getMaster();
		CLUSTER_CONTINER.setMasterUrl(master);
		String[] slaves = ConfigMgr.getSlaves();
		Set<String> slavesSet = new TreeSet<String>();
		slavesSet.addAll(Arrays.asList(slaves));
		CLUSTER_CONTINER.setSalvesUrl(slavesSet);
		CLUSTER_CONTINER.setRole(ConfigMgr.getRole());
	}

	public static ClusterContiner getClusterContiner() {
		return CLUSTER_CONTINER;
	}
}
