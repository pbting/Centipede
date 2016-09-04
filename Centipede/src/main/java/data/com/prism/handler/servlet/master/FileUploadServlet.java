package data.com.prism.handler.servlet.master;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

import data.com.prism.Log;
import data.com.prism.handler.servlet.AbstractServlet;
import data.com.prism.mgr.ConfigMgr;
import data.com.prism.util.FileUtil;
/**
 * 
 * @author pbting
 * receive the data from slave upload
 */
@WebServlet(name="fileUpload",asyncSupported=true,urlPatterns={"/fileUpload.do"})
public class FileUploadServlet extends AbstractServlet {
	public final static String className = FileUploadServlet.class.getName();
	
	private final static AtomicLong COUNT = new AtomicLong(1);
	/**
	 * <pre>
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void executor(HttpServletRequest request, HttpServletResponse response) {
		String filePath = ConfigMgr.getDataPath();
		File uploadFile = new File(filePath);
		FileUtil.checkFile(uploadFile);

		Log.info(className+"; receive slave upload data file,the count is:"+COUNT.getAndIncrement());
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			data.com.prism.Log.error(className, e1);
		}
		response.setCharacterEncoding("utf-8");

		// 检测是不是存在上传文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		List<File> uploadFileList = new ArrayList<File>();
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
			factory.setSizeThreshold(1024 * 1024);

			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
			factory.setRepository(new File(filePath));
			factory.setFileCleaningTracker(new FileCleaningTracker());
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
			upload.setFileSizeMax(100 * 1024 * 1024);

			// 指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
			upload.setSizeMax(100 * 1024 * 1024);
			upload.setHeaderEncoding("UTF-8");

			List<FileItem> items = null;

			try {
				// 解析request请求
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			if (items != null) {
				// 解析表单项目
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 如果是普通表单属性
					if (!item.isFormField()) {
						// 上传文件路径
						String fileName = item.getName();
						// 上传文件路径
						fileName = FileUtil.getSimpleName(fileName);// 获得上传文件的文件名
						
						try {
							File tmpFile = new File(filePath, fileName); 
							item.write(tmpFile);
							uploadFileList.add(tmpFile);
							Log.info(className+"; write "+filePath+File.separator+fileName+" success;file size is:"+item.getSize());
						} catch (Exception e) {
							Log.error(className, e);
						}
					}
				}
			}
		}
		response.addHeader("status", "200");
		//文件上传成功后，解析日志文件数据
		
	}

	@Override
	protected boolean checkIp(String ip, HttpServletResponse response) {
		return true;
	}
}
