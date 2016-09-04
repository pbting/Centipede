package data.com.prism.core;

import org.apache.commons.lang.StringUtils;
import data.com.prism.Log;
import data.com.prism.core.event.AbstractEventObject;
import data.com.prism.core.event.ObjectEvent;
import data.com.prism.core.event.ObjectListener;
import data.com.prism.handler.servlet.EventNameId;
import data.com.prism.handler.servlet.message.DownloadMessage;
import data.com.prism.mgr.ConfigMgr;
import data.com.prism.mgr.MessagePersiterMgr;
import data.com.prism.mgr.TopicPath;
import data.com.prism.util.FileUtil;
/**
 * 
 * @author pbting
 *
 */
public final class EventObjectBuilder {
	
	private EventObjectBuilder(){}
	
	/**
	 * 数据下载
	 * @author pbting
	 *
	 */
	public static class DownloadDataEvent extends AbstractEventObject<DownloadMessage>{
		
		private static final DownloadDataEvent DOWNLOAD_DATA_EVENT = new DownloadDataEvent();
		
		public DownloadDataEvent() {
			addListener(new ObjectListener<DownloadMessage>() {
				
				@Override
				public void onEvent(ObjectEvent<DownloadMessage> event) {
					DownloadMessage downloadMessage = event.getValue();
					String contextPath = downloadMessage.getContextPath();
					String topic = downloadMessage.getTopic();
					String fileName = downloadMessage.getFileName() ;
					if(StringUtils.isNotEmpty(contextPath)&&StringUtils.isNotEmpty(topic)&&StringUtils.isNotEmpty(fileName)){
						String downloadPath = contextPath + "/"+topic + "/" + fileName;
						int failCount = ConfigMgr.getReUploadFail();
						StringBuffer message = null ;
						do {
							message = FileUtil.downLoad(downloadPath);
						} while (message==null&&failCount-->0);
						
						if(message == null){
							Log.error("download message fail.the url is:"+downloadPath);
							return ;
						}
						
						if(MessagePersiterMgr.getFilePersistListener(topic) == null){
							MessagePersiterMgr.init(new TopicPath(topic, ConfigMgr.getMsgPath()));
						}
						MessagePersiterMgr.persister(topic, downloadMessage.getKey(), message, (short)0);
					}
				}
			}, EventNameId.DOWNLOAD_DATA);
		}
		
		public void notifyDowload(DownloadMessage downloadMessage){
			ObjectEvent<DownloadMessage> objectEvent = new ObjectEvent<DownloadMessage>(this,downloadMessage, EventNameId.DOWNLOAD_DATA);
			notifyListeners(objectEvent);
		}
	}
	
	public static DownloadDataEvent getDownloadDataEvent(){
		
		return DownloadDataEvent.DOWNLOAD_DATA_EVENT;
	}
	public static void main(String[] args) {
		DownloadMessage downloadMessage = new DownloadMessage("demo", 1, "key", "D:/demo.txt", "Centipede");
		getDownloadDataEvent().notifyDowload(downloadMessage);
	}
}
