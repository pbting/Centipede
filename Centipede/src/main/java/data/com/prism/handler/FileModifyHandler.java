package data.com.prism.handler;

import data.com.prism.Log;
import data.com.prism.core.ExecutorBuilder;
import data.com.prism.exception.TaskExecuteException;
import data.com.prism.handler.topic.ErrorTopicModifyHandler;
import data.com.prism.mgr.TopicHandlerMappingMgr;

public class FileModifyHandler extends FileHandler{
	public FileModifyHandler(String dir,String filePath){
		super(dir,filePath);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void execute(String dir, String fileName) throws TaskExecuteException {
		if(!dir.endsWith("/")){
			dir += "/";
		}
		String absoluPath = dir+fileName;
		@SuppressWarnings("rawtypes")
		AnalylizeLogInter handler = TopicHandlerMappingMgr.getTopicHandler(absoluPath);
		if(handler != null){
			String topic = TopicHandlerMappingMgr.getTopicName(absoluPath);
			ExecutorBuilder.TopicHandlerExecutor.getExecutor().enDefaultQueue(new ErrorTopicModifyHandler(handler,topic,absoluPath,false));
		}else{
			Log.error("[ "+absoluPath+" ] 没有相应的分析器.");
		}
	}
}
