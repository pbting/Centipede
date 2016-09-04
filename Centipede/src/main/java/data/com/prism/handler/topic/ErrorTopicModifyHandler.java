package data.com.prism.handler.topic;

import data.com.prism.core.LogDetailInfo;
import data.com.prism.core.LogMetaInfo;
import data.com.prism.core.MappedBufferReadFile;
import data.com.prism.handler.AnalylizeLogInter;
import data.com.prism.handler.FileStatusMgr;

/**
 * <pre>
 * 	这个只对error 文件进行监测
 * </pre>
 */
public class ErrorTopicModifyHandler extends TopicHandler {
	public ErrorTopicModifyHandler(AnalylizeLogInter<LogMetaInfo, LogDetailInfo> analysor, String topic, String fileName, boolean isRepeat) {
		super(analysor, topic, fileName, isRepeat);
	}

	@Override
	public long execute(String topic, String fileName) {
		try {
			int lastReadIndex = FileStatusMgr.getMapPosition(topic, fileName);// 获取上一次文件解析的位置
			int nextReadIndex = MappedBufferReadFile.readAnalysis(analysor, fileName, topic, lastReadIndex, isRepeat);
			FileStatusMgr.setMapPosition(topic, fileName, nextReadIndex);// 更新当前解析的位置
			return nextReadIndex;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}