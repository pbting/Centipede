package data.com.prism.handler;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

public abstract class TopicMap<K,V> implements AnalylizeLogInter<K,V>{

	public final Map<K, List<V>> analylize(String srcLogInfo) {
		StringTokenizer st = new StringTokenizer(srcLogInfo, "\r\n");
		Map<K,List<V>> logInfos = new ConcurrentHashMap<K,List<V>>();
		while(st.hasMoreTokens()){
			map(st.nextToken(), logInfos,st.hasMoreTokens());
		}
		return logInfos;
	}

	public abstract void map(String line,Map<K,List<V>> logContext,boolean hasNextToken);
}
