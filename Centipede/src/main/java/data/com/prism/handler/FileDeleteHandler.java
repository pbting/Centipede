package data.com.prism.handler;

import data.com.prism.exception.TaskExecuteException;

public class FileDeleteHandler extends FileHandler{

	public FileDeleteHandler(String topic,String filePath){
		super(topic,filePath);
	}
	
	@Override
	public void execute(String dir, String fileName) throws TaskExecuteException {
	}
}
