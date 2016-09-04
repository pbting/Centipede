package data.com.prism.handler;

import data.com.prism.core.event.ObjectEvent;
import data.com.prism.core.event.ObjectListener;
import data.com.prism.core.quence.Action;
import data.com.prism.exception.TaskExecuteException;

public class EventHandler<V> extends Action{
	private ObjectListener<V> objectListener ;
	private ObjectEvent<V> event;
	public EventHandler(ObjectListener<V> objectListener,ObjectEvent<V> event) {
		this.objectListener = objectListener;
		this.event = event;
	}
	
	@Override
	public long execute() throws TaskExecuteException {
		long start = System.currentTimeMillis();
		objectListener.onEvent(event);
		return System.currentTimeMillis()-start;
	}
}
