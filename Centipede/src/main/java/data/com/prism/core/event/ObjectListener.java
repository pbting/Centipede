package data.com.prism.core.event;

import java.util.EventListener;

public interface ObjectListener<V> extends EventListener {
	
	public void onEvent(ObjectEvent<V> event);

}
