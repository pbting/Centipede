package data.com.prism.handler.servlet.message;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;
	protected String topic;
	protected int eventId ;
	public Message() {
	}
	
	public Message(String topic,int eventId) {
		super();
		this.topic = topic;
		this.eventId = eventId;
	}

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
}
