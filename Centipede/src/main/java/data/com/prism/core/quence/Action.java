package data.com.prism.core.quence;

import data.com.prism.Log;
import data.com.prism.exception.TaskExecuteException;

public abstract class Action implements Runnable {

	protected ActionQueue queue;
	protected Long createTime;
	
	public Action() {
		createTime = System.currentTimeMillis();
	}
	
	public Action(ActionQueue queue) {
		this.queue = queue;
		createTime = System.currentTimeMillis();
	}
	
	public void setActionQueue(ActionQueue queue){
		this.queue = queue;
	}
	
	public ActionQueue getActionQueue() {
		return queue;
	}

	@Override
	public void run() {
		if (queue != null) {
			long start = System.currentTimeMillis();
			try {
				execute();
				long end = System.currentTimeMillis();
				long interval = end - start;
				long leftTime = end - createTime;
				if (interval >= 1000) {
					Log.warn("execute action : " + this.toString() + ", interval : " + interval + ", leftTime : " + leftTime + ", size : " + queue.getQueue().size());
				}
			} catch (Exception e) {
				Log.error("run action execute exception. action : " + this.toString(), e);
			} finally {
				queue.dequeue(this);
			}
		}
	}

	public abstract long execute() throws TaskExecuteException;
}
