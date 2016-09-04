package data.com.prism.handler;

import data.com.prism.exception.TaskExecuteException;

public class FileCreateHandler extends FileHandler{

	public FileCreateHandler(String topic,String filePath){
		super(topic,filePath);
	}

	@Override
	public void execute(String dir, String fileName) throws TaskExecuteException {

	}
}
