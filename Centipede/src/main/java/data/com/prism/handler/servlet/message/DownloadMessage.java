package data.com.prism.handler.servlet.message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * slave notify master to download message
 * @author pbting
 *
 */
public class DownloadMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key ;
	private String fileName ;
	private String contextPath ;
	public DownloadMessage() {
	}
	
	public DownloadMessage(String topic,int eventId,String key,String fileName, String contextPath) {
		super(topic,eventId);
		this.key = key;
		this.fileName = fileName;
		this.contextPath = contextPath;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "DownloadMessage [fileName=" + fileName + ", contentPath=" + contextPath + ", topic=" + topic + ", eventId=" + eventId +", key=" + key +"]";
	}
	public static void main(String[] args) {
		Message dow = new DownloadMessage("demo", 1, "key","D:/demo.txt", "Contipede");
		String param = new Gson().toJson(dow);
		TypeToken<Message> typeToken = new TypeToken<Message>(){};
		Message requestMessage = new Gson().fromJson(param,typeToken.getType());
		System.out.println(requestMessage);
		DownloadMessage tmpM = (DownloadMessage) requestMessage;
	}
}
