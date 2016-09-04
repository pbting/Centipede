package data.com.prism.handler;

import java.util.Map;

import data.com.prism.core.quence.Action;
import data.com.prism.exception.TaskExecuteException;
import data.com.prism.mgr.TopicHandlerMappingMgr;

public class ConsumerData<K,V> extends Action{
	private Map<K,V> data ;
	private String topic ;
	public ConsumerData(String topic,Map<K,V> data){
		this.data = data ;
		this.topic = topic;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long execute() throws TaskExecuteException {
		long start = System.currentTimeMillis();
		for (Map.Entry<K,V> entry : data.entrySet()) {
			TopicHandlerMappingMgr.getTopicReduce(topic).reduce(entry.getKey(), entry.getValue());
		}
		return System.currentTimeMillis()-start;
	}
}
