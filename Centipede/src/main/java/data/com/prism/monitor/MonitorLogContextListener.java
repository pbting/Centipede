package data.com.prism.monitor;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import data.com.prism.Log;
import data.com.prism.bank.ShutDownHook;
import data.com.prism.cluster.ClusterMgr;
import data.com.prism.mgr.ConfigMgr;
import data.com.prism.mgr.TopicHandlerMappingMgr;
import data.com.prism.mgr.TopicListenerMgr;
import data.com.prism.util.StringUtil;

public class MonitorLogContextListener implements ServletContextListener {
	
	private static final String logAnalyseConfig = "logAnalyseConfig";
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent context) {
		String dataPath = context.getServletContext().getRealPath("/") + File.separator + "data" + File.separator;
		//1、
		ConfigMgr.init(context.getServletContext().getInitParameter(logAnalyseConfig),dataPath);
		
		//2、
		if(StringUtil.isEmpty(ConfigMgr.getMonitorDir())){
			throw new IllegalArgumentException("监听目录不能为空");
		}
		
		String[] paths = ConfigMgr.getMonitorDir().split(";");
		try {
			TopicListenerMgr.addListener(paths);
		} catch (IOException e) {
			Log.error("", e);
		}
		
		//4、
		PropertyConfigurator.configure(ConfigMgr.getlog4jPath());
		TopicHandlerMappingMgr.setMonitorPaths(paths);
		//5、
		TopicHandlerMappingMgr.init(ConfigMgr.getTopicHandler());
		
		//6、
		Runtime.getRuntime().addShutdownHook(new ShutDownHook());
		
		//7、集群管理
		ClusterMgr.init();
	}
}
