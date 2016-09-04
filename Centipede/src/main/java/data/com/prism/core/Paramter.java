package data.com.prism.core;

public interface Paramter {

	public final static String logAnalyseConfig = "logAnalyseConfig";
	
	public final static String TRANS_MODE_POST = "post";
	
	public final static String TRANS_MODE_UPLOAD = "upload";
	
	public final static String ROLE_MASTER = "master" ;
	
	public final static String ROLE_SLAVE = "slave" ;
	
	public final static String EVENT_ID = "eventId" ;
	
	public final static String PARAM = "param";
	
	public final static String WEB_DATA_PATH = "webDataPath" ;
	
	/**----------------------SERVLET URL--------------------------------**/
	
	public final static String URL_fileUpload = "/fileUpload.do";
	
	public final static String URL_masterEventHandler = "/masterEventHandler.do";
}
