package data.com.prism.servlet;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.com.prism.LogDateComparator;
import data.com.prism.handler.servlet.AbstractServlet;
import data.com.prism.servlet.service.ConsumerLogFactory;
import data.com.prism.vo.LogKeyInfo;

@WebServlet(name="MonitorLogServlet",asyncSupported=true,urlPatterns={"/monitorLogServlet"})
public class MonitorLogServlet extends AbstractServlet{

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void executor(HttpServletRequest request, HttpServletResponse response) {
		
		String topic = request.getParameter("topic");
		
		HashMap<LogKeyInfo, Integer> keyCount = (HashMap<LogKeyInfo, Integer>) ConsumerLogFactory.countMap.get(topic);
		if(keyCount!=null){
			TreeSet<LogKeyInfo> treeSet = new TreeSet<LogKeyInfo>(new LogDateComparator());
			for(Entry<LogKeyInfo, Integer> enrty : keyCount.entrySet()){
				treeSet.add(new LogKeyInfo(enrty.getKey().getPrintDate(),enrty.getKey().getKey(), enrty.getValue()));
			}
			String jsonStr = new Gson().toJson(treeSet);
			sendMessage(response, jsonStr);
		}
	} 
	
	@Override
	protected boolean checkIp(String ip, HttpServletResponse response) {
		return true;
	}
}
