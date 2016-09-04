package data.com.prism.bank.persister;

import data.com.prism.bank.DataBankMgr;
import data.com.prism.core.quence.Action;
import data.com.prism.exception.TaskExecuteException;

public class Persister extends Action{
	private String topic;
	private Object key;
	private Object message;
	private String filePath;
	private short type;
	public Persister(String topic, Object key, Object message, String filePath, short type) {
		this.topic = topic;
		this.key = key;
		this.message = message;
		this.filePath = filePath;
		this.type = type;
	}

	@Override
	public long execute() throws TaskExecuteException {
		DataBankMgr.store(topic,key,message, filePath,type);
		return 1 ;
	}
}
