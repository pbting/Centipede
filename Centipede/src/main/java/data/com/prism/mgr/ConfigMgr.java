package data.com.prism.mgr;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

import data.com.prism.Log;
import data.com.prism.core.Paramter;
import data.com.prism.util.StringUtil;

public class ConfigMgr {
	private static Properties properties = null ;
	/**
	 * 
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param path 配置文件的路径
	 * @param dataPath 日志数据存储的路径
	 */
	public static boolean init(String path,String dataPath,String contextPath){
		if(!StringUtil.isEmpty(path)){
			try {
				InputStream is = new BufferedInputStream(new FileInputStream(path));
				properties = new Properties();
				properties.load(is);
				if(StringUtils.isEmpty(dataPath)){
					Log.error("the path of data store [dataPath]"+dataPath+"; is null,please config the dataPath");
					return false;
				}
				properties.put("dataPath", dataPath);
				
				if(StringUtils.isEmpty(contextPath)){
					Log.error("the contentPath [contentPath]"+contextPath+"; is null,please input the contentPath");
				}
				properties.put("contextPath", contextPath);
			} catch (IOException e) {
				Log.error(ConfigMgr.class.getName(), e);
				return false;
			}
			return true; 
		}
		return false;
	}
	
	public static boolean init(ServletContext servletContext){
		String path = servletContext.getInitParameter(Paramter.logAnalyseConfig);
		String dataPath = servletContext.getRealPath("/") + File.separator + "data" + File.separator;
		String contextPath = servletContext.getContextPath();
		if(!StringUtil.isEmpty(path)){
			try {
				InputStream is = new BufferedInputStream(new FileInputStream(path));
				properties = new Properties();
				properties.load(is);
				if(StringUtils.isEmpty(dataPath)){
					Log.error("the path of data store [dataPath]"+dataPath+"; is null,please config the dataPath");
					return false;
				}
				properties.put(Paramter.WEB_DATA_PATH, dataPath);
				
				if(StringUtils.isEmpty(contextPath)){
					Log.error("the contentPath [contentPath]"+contextPath+"; is null,please input the contentPath");
				}
				properties.put("contextPath", contextPath);
				
			} catch (IOException e) {
				Log.error(ConfigMgr.class.getName(), e);
				return false;
			}
			return true; 
		}
		return false;
	}
	public static String getMsgHouseConfig(){
		return properties.getProperty("msghourse");
	}
	
	public static String getlog4jPath(){
		return properties.getProperty("log4j.path");
	}
	
	public static String getTopicHandler(){
		return properties.getProperty("topic_handler");
	}
	
	public static String getMonitorDir(){
		
		return properties.getProperty("monitor.path");
	}
	
	public static String getTopics(){
		return properties.getProperty("topics");
	}
	
	public static String getMsgPath(){
		return properties.getProperty("msg.path");
	}
	
	public static String getMaster(){
		return properties.getProperty("master");
	}
	public static String getSlave(){
		return properties.getProperty("slave");
	}
	
	public static String getRole(){
		String role = properties.getProperty("role");
		if(StringUtil.isEmpty(role)){
			role = "master";
		}
		return role;
	}
	
	public static String getTransMode(){
		String transMode = properties.getProperty("trans.mode");
		if(StringUtil.isEmpty(transMode)){
			transMode = "download";//default transfer mode is download
		}
		return transMode;
	}
	
	public static String getWebDataPath(){
		
		return properties.getProperty(Paramter.WEB_DATA_PATH);
	}
	
	public static int getReUploadFail(){
		String count = properties.getProperty("reupload.fail");
		if(!StringUtil.isEmpty(count)&&StringUtil.isNumeric(count)){
			
			return Integer.parseInt(count);
		}
		
		return 3 ;
	}
	
	public static String getContextPath(){
		
		return properties.getProperty("contextPath");
	}
}
