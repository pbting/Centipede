package data.com.prism.bank.persister;

import java.io.File;
import org.apache.commons.lang.StringUtils;
import com.google.gson.reflect.TypeToken;
import data.com.prism.bank.DataBankMgr;
import data.com.prism.bank.MD5Security;
import data.com.prism.core.ExecutorBuilder;
import data.com.prism.exception.FilePersistException;

public abstract class AbstractFilePersistListener implements FilePersistListener {

	// 
	protected final static String FILE_EXTION = ".cache";

	// application
	protected final static String APPLICATION_CACHE_PATH = "prism";

	//
	protected File cachePath = null;

	// compose of base path and topic
	private String rootPath = null;

	public void clear() {

		this.clear(rootPath);
	}

	/**
	 * @param rootDirName
	 * @throws FilePersistException
	 */
	private void clear(String rootDirName)  {
		
		data.com.prism.Log.debug(":[clear="+rootDirName+"]");
		
		File rootFile = new File(rootDirName);

		File[] fileList = rootFile.listFiles();

		// 
		try {
			if (fileList != null) {

				for (int i = 0; i < fileList.length; i++) {

					if (fileList[i].isFile()) {

						fileList[i].delete();
					} else {

						this.clear(fileList[i].toString());//
						fileList[i].delete();//
					}
				}
			}

			//
			rootFile.delete();
		} catch (Exception e) {
			data.com.prism.Log.error("clear:root path:"+rootDirName,e);
		}
	}

	public FilePersistListener config(String topic,String path) {

		initFileStore(path);
		StringBuffer root = new StringBuffer(getCachePath().getPath());
		root.append(File.separator);
		root.append(StringUtils.isEmpty(topic)?APPLICATION_CACHE_PATH:topic);
		this.rootPath = root.toString();
		return this;
	}

	public File getCachePath() {
		return cachePath;
	}

	public boolean isStored(String key) {
		try {
			File file = this.getCacheFile(key);

			return file.exists();
		} catch (Exception e) {
			data.com.prism.Log.error("is Stores：【key】:"+key);
			return false;
		}
	}

	public boolean remove(String key) {
		try {
			File file = this.getCacheFile(key);
			
			this.remove(file);

			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public Object retrieve(String topic,Object key,short type,TypeToken<?> typeToken) {
		
		File file = this.getCacheFile(MD5Security.compute(key.toString()));
		
		return DataBankMgr.retrieve(topic,key,file.getAbsolutePath(),type,typeToken);
	}

	public void store(String topic,Object key, Object value,short type) {
		this.MsgStore(topic,key,this.getCacheFile(MD5Security.compute(topic)),value,type);
	}

	// will get the file of cache directly
	protected File getCacheFile(String key) {
		char[] fileName = this.getCacheFileName(key);

		return new File(rootPath, new String(fileName) + FILE_EXTION);
	}

	// will based on group name get the file of the related group file
	protected abstract char[] getCacheFileName(String key);

	/**
	 * init file cacheing by the file path
	 * @param cacheFilePath
	 */
	protected void initFileStore(String cacheFilePath) {
		if (cacheFilePath != null) {
			this.cachePath = new File(cacheFilePath);
			System.err.println("cache file path is :"+this.cachePath.getAbsolutePath());
			try {

				if (!this.cachePath.exists()) {//不存在，则创建
					data.com.prism.Log.info("HighCache:the file directory of cache is:" + cacheFilePath + "");
					//create new dirs
					this.cachePath.mkdirs();

				}

				// judge the path whether is directory or not 
				if (!this.cachePath.isDirectory()) {//如果配置的不是目录，则删除，并置为null
					data.com.prism.Log.error("[" + this.cachePath.getAbsolutePath()+ "]");
					this.cachePath = null;
					return ;
				}

				// 
				if (!this.cachePath.canWrite()) {
					data.com.prism.Log.error("[" + this.cachePath.getAbsolutePath()+ "]");

					this.cachePath = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 */
	private static final long DELETE_THREAD_SLEEP = 500;

	private static final int DELETE_COUNT = 60;

	protected void remove(File file) throws FilePersistException {
		int count = DELETE_COUNT;

		try {
			//
			while (file.exists() && !file.delete() && count != 0) {
				count--;

				try {
					Thread.sleep(DELETE_THREAD_SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (file.exists() && count == 0) {

			throw new FilePersistException("HighCache:[" + file.getName() + "]cano't delete it");
		}
	}

	public void MsgStore(String topic,Object key,File file,Object value,short type){
		ExecutorBuilder.PersisterExecutor.getExecutor().enDefaultQueue(new Persister(topic, key, value, file.getAbsolutePath(), type));
	}
}
