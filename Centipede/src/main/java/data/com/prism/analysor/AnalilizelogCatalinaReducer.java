package data.com.prism.analysor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.com.prism.Log;
import data.com.prism.core.LogDetailInfo;
import data.com.prism.core.LogMetaInfo;
import data.com.prism.handler.Reducer;
import data.com.prism.mgr.MessagePersiterMgr;
import data.com.prism.mgr.TopicHandlerMappingMgr;
import data.com.prism.servlet.service.ConsumerLogFactory;
import data.com.prism.vo.LogKeyInfo;

public class AnalilizelogCatalinaReducer implements Reducer<LogMetaInfo, List<LogDetailInfo>> {

	@Override
	public void reduce(LogMetaInfo k, List<LogDetailInfo> v) {
		String topicName = TopicHandlerMappingMgr.getReducerTopicName(AnalilizelogCatalinaReducer.class.getName());
		try {
			MessagePersiterMgr.persister(topicName, k.getKey(), v,(short) 0);
			Map<LogKeyInfo,Integer> keyCountMap = ConsumerLogFactory.countMap.get(topicName);
			if(keyCountMap == null){
				keyCountMap = new HashMap<LogKeyInfo,Integer>();
				ConsumerLogFactory.countMap.put(topicName, keyCountMap);
			}
			Integer count = keyCountMap.get(new LogKeyInfo(k.getKey()));
			if(count == null){
				count = 0 ;
			}
			keyCountMap.put(new LogKeyInfo(v.get(v.size()-1).getOccurDate(), k.getKey(), count+v.size()), count+v.size());
		} catch (Exception e) {
			Log.error("", e);
		}
	}
}
