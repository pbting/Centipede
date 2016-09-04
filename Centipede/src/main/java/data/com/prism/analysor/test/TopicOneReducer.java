package data.com.prism.analysor.test;

import java.util.List;

import data.com.prism.handler.Reducer;

//@TopicReduce(topic="topicOne")
public class TopicOneReducer implements Reducer<String,List<Person>> {

	@Override
	public void reduce(String k, List<Person> v) {
		TopicHouseMgr.getExecutor().enDefaultQueue(new PutMessage<Person>("topicOne", k, v));
		//到时候这里可能会在其他地方来消费刚刚put 进去的消息
		TopicHouseMgr.getExecutor().enDefaultQueue(new TakeMessage("topicOne", k));
	}
}
