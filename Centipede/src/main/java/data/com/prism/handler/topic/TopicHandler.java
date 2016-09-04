package data.com.prism.handler.topic;

import data.com.prism.core.LogDetailInfo;
import data.com.prism.core.LogMetaInfo;
import data.com.prism.core.quence.Action;
import data.com.prism.exception.TaskExecuteException;
import data.com.prism.handler.AnalylizeLogInter;

public abstract class TopicHandler extends Action{
	protected String topic;
	protected String fileName ;
	protected AnalylizeLogInter<LogMetaInfo, LogDetailInfo> analysor ;
	protected boolean isRepeat ;
	public TopicHandler(AnalylizeLogInter<LogMetaInfo, LogDetailInfo> analysor,String topic,String fileName,boolean isRepeat){
		this.topic = topic;
		this.fileName = fileName;
		this.analysor = analysor;
		this.isRepeat = isRepeat;
	}

	@Override
	public long execute() throws TaskExecuteException {
		return this.execute(topic, fileName);
	}
	
	public abstract long execute(String topic,String fileName);
}
