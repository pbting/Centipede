package data.com.prism.bank.persister;

import java.util.List;
import java.util.concurrent.Callable;

import com.google.gson.reflect.TypeToken;

import data.com.prism.Log;
import data.com.prism.bank.DataBankMgr;
import data.com.prism.core.quence.Action;
import data.com.prism.exception.TaskExecuteException;

public class Retrive<V> extends Action implements Callable<List<V>>{
	private String topic;
	private Object key; 
	private String fileName; 
	private short type;
	private TypeToken<?> typeToken;
	public Retrive(String topic, Object key, String fileName, short type, TypeToken<V> typeToken) {
		this.topic = topic;
		this.key = key;
		this.fileName = fileName;
		this.type = type;
		this.typeToken = typeToken;
	}
	
	@Override
	public long execute() throws TaskExecuteException {
		try {
			call();
		} catch (Exception e) {
			Log.error("", e);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V> call() throws Exception {
		return (List<V>) DataBankMgr.retrieve(topic,key,fileName,type,typeToken);
	}
}
