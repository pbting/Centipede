package data.com.prism.handler.servlet.message;

/**
 * slave 将消息上传给master的时候，又该消息进行封装
 * @author pbting
 *
 */
public class TransforMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StringBuffer message;
	
	private String filePath ;
	
	public TransforMessage(String topic,int eventId,String filePath,StringBuffer message) {
		super(topic,eventId);
		this.filePath = filePath;
		this.message = message;
	}
	public StringBuffer getMessage() {
		return message;
	}
	public void setMessage(StringBuffer message) {
		this.message = message;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
