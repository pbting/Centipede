package data.com.prism.mgr;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import data.com.prism.monitor.ResourceListener;

public class TopicListenerMgr {

	private TopicListenerMgr(){}
	
	/**
	 * 
	 * <pre>
	 * 	can listener signal path
	 * </pre>
	 *
	 * @param path
	 * @throws IOException
	 */
	public static void addListener(String path) throws IOException {
		if(path != null && path.trim().length()>0){
			ResourceListener.addListener(path);
		}
	}

	/**
	 * 
	 * <pre>
	 * can listener more path
	 * </pre>
	 *
	 * @param paths
	 * @throws IOException
	 */
	public static void addListener(Set<String> paths) throws IOException {
		if(paths != null && !paths.isEmpty()){
			for (Iterator<String> iter=paths.iterator();iter.hasNext();) {
				addListener(iter.next());
			}
		}
	}
	public static void addListener(String... paths) throws IOException{
		if(paths != null && paths.length>0){
			for (String path:paths) {
				addListener(path);
			}
		}
	}
}
