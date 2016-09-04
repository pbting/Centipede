package data.com.prism.handler.servlet.message;

public class RequestMessage extends Message{

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;

	private String fileName ;
	
	public RequestMessage() {
	}
	
	public RequestMessage(String topic, String fileName, int eventId) {
		super(topic,eventId);
		this.fileName = fileName;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
