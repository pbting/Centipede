package data.com.prism.analysor.filter;

import data.com.prism.handler.filter.FileHandlerFilter;

public class ErrorFileFilter implements FileHandlerFilter {

	public boolean accept(String fileName) {
		return fileName.endsWith("error.log");
	}
}
