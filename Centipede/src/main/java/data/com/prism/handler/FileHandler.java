package data.com.prism.handler;

import data.com.prism.core.quence.Action;
import data.com.prism.core.quence.ActionQueue;
import data.com.prism.exception.TaskExecuteException;

public abstract class FileHandler extends Action {

	protected String fileName ;
	protected String dir ;
	public FileHandler(String dir ,String filePath){
		this.dir = dir;
		this.fileName = filePath;
	}
	
	public FileHandler(ActionQueue queue) {
		super(queue);
	}

	@Override
	public long execute() throws TaskExecuteException {
		execute(dir,fileName);
		return 0;
	}
	public abstract void execute(String dir,String fileName) throws TaskExecuteException ;
}
