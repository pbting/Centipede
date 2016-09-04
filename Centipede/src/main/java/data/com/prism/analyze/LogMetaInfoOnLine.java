package data.com.prism.analyze;

import data.com.prism.core.LogMetaInfo;

public class LogMetaInfoOnLine extends LogMetaInfo{

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getKey() {
		
		return this.callClassName+"->"+ this.callMethodName;
	}
}
