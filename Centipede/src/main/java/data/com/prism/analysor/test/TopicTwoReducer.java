package data.com.prism.analysor.test;

import java.util.List;

import data.com.prism.handler.Reducer;

//@TopicReduce(topic = "topicTwo")
public class TopicTwoReducer implements Reducer<String, List<Person>> {

	@Override
	public void reduce(String k, List<Person> v) {
		TopicHouseMgr.getExecutor().enDefaultQueue(new PutMessage<Person>("topicTwo", k, v));
		TopicHouseMgr.getExecutor().enDefaultQueue(new TakeMessage("topicTwo", k));
	}
}
