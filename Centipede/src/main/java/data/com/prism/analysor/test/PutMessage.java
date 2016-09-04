package data.com.prism.analysor.test;

import java.util.List;

import data.com.prism.core.quence.Action;
import data.com.prism.exception.TaskExecuteException;

public class PutMessage<V> extends Action{
	
	String topic;
	String key;
	List<V> msg;
	public PutMessage(String topic, String key, List<V> msg){
		this.topic = topic;
		this.key = key;
		this.msg = msg;
	}
	
	@Override
	public long execute() throws TaskExecuteException {
		TopicHouseMgr.put(topic, key, msg);
		return 0;
	}
}
