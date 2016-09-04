package data.com.prism.handler.servlet.master;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import data.com.prism.core.EventObjectBuilder;
import data.com.prism.core.Paramter;
import data.com.prism.handler.servlet.AbstractServlet;
import data.com.prism.handler.servlet.EventNameId;
import data.com.prism.handler.servlet.message.DownloadMessage;
import data.com.prism.util.StringUtil;

@WebServlet(name = "masterEventHandler", asyncSupported = true, urlPatterns = { "/masterEventHandler.do" })
public class MasterReciveEventHandler extends AbstractServlet {

	/**
	 * <pre>
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void executor(HttpServletRequest request, HttpServletResponse response) {
		String eventId = request.getParameter(Paramter.EVENT_ID);
		if(StringUtils.isEmpty(eventId)&&!StringUtil.isNumeric(eventId)){
			return ;
		}
		
		short eventIdS = Short.parseShort(eventId);
		String param = request.getParameter(Paramter.PARAM);
		if (!StringUtil.isEmpty(param)) {
			switch (eventIdS) {
			case EventNameId.DOWNLOAD_DATA://master download
			{
				DownloadMessage downloadMessage = new Gson().fromJson(param,new TypeToken<DownloadMessage>(){}.getType());
				EventObjectBuilder.getDownloadDataEvent().notifyDowload(downloadMessage);
			}
			break;
			}
		}
	}

	@Override
	protected boolean checkIp(String ip, HttpServletResponse response) {
		return true;
	}

	private boolean checkParam(int eventId, String topic) {
		boolean flag = true;
		flag |= eventId > 0;
		flag |= StringUtil.isEmpty(topic);
		return flag;
	}
}
