package data.com.prism.core.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import data.com.prism.Log;
import data.com.prism.core.ExecutorBuilder;
import data.com.prism.handler.EventHandler;

public abstract class AbstractEventObject<V> {
	private ConcurrentHashMap<Integer, Collection<ObjectListener<V>>> listeners;
	private static boolean isDebug = false;
	private Object lock = new Object();

	public void addListener(ObjectListener<V> objectListener, int eventType) {
		synchronized (lock) {
			if (listeners == null) {
				listeners = new ConcurrentHashMap<Integer, Collection<ObjectListener<V>>>();
			}
		}

		if (listeners.get(eventType) == null) {
			Collection<ObjectListener<V>> tempInfo = new HashSet<ObjectListener<V>>();
			tempInfo.add(objectListener);
			listeners.put(eventType, tempInfo);
		} else {
			listeners.get(eventType).add(objectListener);
		}
		debugEventMsg("注册一个事件,类型为" + eventType);
	}

	public void removeListener(ObjectListener<V> objectListener, int eventType) {
		if (listeners == null)
			return;
		Collection<ObjectListener<V>> tempInfo = listeners.get(eventType);
		if (tempInfo != null) {
			tempInfo.remove(objectListener);
		}
		debugEventMsg("移除一个事件,类型为" + eventType);
	}

	public void notifyListeners(ObjectEvent<V> event) {
		List<ObjectListener<V>> tempList = null;
		if (listeners == null)
			return;
		int eventType = event.getEventType();
		if (listeners.get(eventType) != null) {
			Collection<ObjectListener<V>> tempInfo = listeners.get(eventType);
			tempList = new ArrayList<ObjectListener<V>>();
			Iterator<ObjectListener<V>> iter = tempInfo.iterator();
			while (iter.hasNext()) {
				ObjectListener<V> listener =  iter.next();
				tempList.add(listener);
			}
		}
		// 触发
		if (tempList != null) {
			for (ObjectListener<V> listener : tempList) {
				ExecutorBuilder.HanderExecutor.getExecutor().enDefaultQueue(new EventHandler<V>(listener, event));
			}
		}
	}

	public void clearListener() {
		synchronized (lock) {
			if (listeners != null) {
				listeners = null;
			}
		}
	}

	public void debugEventMsg(String msg) {
		if (isDebug) {
			Log.info(msg);
		}
	}
}