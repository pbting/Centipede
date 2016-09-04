package data.com.prism.mgr;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.reflect.TypeToken;

import data.com.prism.bank.DataBankMgr;
import data.com.prism.bank.persister.FilePersistListener;
import data.com.prism.bank.persister.GeneralDiskPersistListener;

public class MessagePersiterMgr {

	private MessagePersiterMgr(){}
	
	private static final Map<String,FilePersistListener> topicFilePMap = new ConcurrentHashMap<String,FilePersistListener>();
	
	public static void init(TopicPath...topicPaths){
		for(TopicPath tp : topicPaths){
			DataBankMgr.init(tp.getTopic(),tp.getPath());
			FilePersistListener fp = new GeneralDiskPersistListener();
			fp.config(tp.getTopic(), tp.getPath());
			topicFilePMap.put(tp.getTopic(),fp);
		}
		DataBankMgr.registerTask();
	}
	
	public static void persister(String topic,Object key,Object value,short type){
		if(topicFilePMap.containsKey(topic)){
			topicFilePMap.get(topic).store(topic, key, value, type);
		}
	}
	
	public static Set<String> getAllTopics(){
		return topicFilePMap.keySet();
	}
	
	
	public static<T> Object retrive(String topic,Object key,short type,TypeToken<T> typeToken){
		if(topicFilePMap.containsKey(topic)){
			return topicFilePMap.get(topic).retrieve(topic, key,type,typeToken);
		}
		return null ;
	}
	
	
	public static void main(String[] args) {
		init(new TopicPath("countOne", "D:/prism/count"));
	}
}
