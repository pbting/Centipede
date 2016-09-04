package data.com.prism;

import java.util.Comparator;

import data.com.prism.vo.LogKeyInfo;

public class LogDateComparator implements Comparator<LogKeyInfo>{

	@Override
	public int compare(LogKeyInfo o1, LogKeyInfo o2) {
		
		int key = o2.getPrintDate().compareTo(o1.getPrintDate());
		
		return key==0 ? o2.getKey().compareTo(o1.getKey()):key;
	}
}
