package data.com.prism.core;

import java.util.Map;

import data.com.prism.core.quence.Executor;
import data.com.prism.handler.ConsumerData;
import data.com.prism.handler.ForwardMessage;

public final class ExecutorBuilder {

	private ExecutorBuilder(){}
	
	private final static int corePoolSize = 8;
	
	private final static int maxPoolSize = 16;
	
	private final static int keepAliveTime = 5;
	
	private final static int cacheSize = 64;
	/**
	 * 
	 * @author pbting
	 *
	 */
	public static final class PersisterExecutor {

		private PersisterExecutor(){}
		private static Executor executor;
		
		public static Executor getExecutor(){
			if(executor == null){
				synchronized (PersisterExecutor.class) {
					if(executor  ==null){
						executor = new Executor(corePoolSize, maxPoolSize, keepAliveTime,cacheSize, "persister-executor-log");
					}
				}
			}
			return executor;
		}
	}
	/**
	 * 
	 * @author pbting
	 *
	 */
	public static final class ConsumerExecutor {

		private ConsumerExecutor() {}

		private static Executor executor;
		static{
			int corePoolSize = Runtime.getRuntime().availableProcessors()*2+1;
			int maxPoolSize = 2 * corePoolSize;
			executor = new Executor(corePoolSize,maxPoolSize, keepAliveTime,cacheSize, "consume-executor-log");	
		}
		/**
		 * 
		 * <pre>
		 * 	when role is master then call the method to handler the log message
		 * </pre>
		 *
		 * @param topic
		 * @param logs
		 */
		public static <K, V> void consumer(String topic, Map<K,V> logs) {
			executor.enDefaultQueue(new ConsumerData<K,V>(topic, logs));
		}
		
		public static void forward(String topic,String abFilePath,StringBuilder message){
			executor.enDefaultQueue(new ForwardMessage(topic,abFilePath,message));
		}
	}
	/**
	 * 
	 * @author pbting
	 *
	 */
	public static class HanderExecutor {

		private HanderExecutor() {}

		private static Executor executor;
		
		public static Executor getExecutor(){
			if(executor == null){
				synchronized (HanderExecutor.class) {
					if(executor == null){
						executor = new Executor(corePoolSize, maxPoolSize, keepAliveTime,cacheSize, "filehander-executor-prsim");
					}
				}
			}
			return executor;
		}
	}
	/**
	 * 
	 * @author pbting
	 *
	 */
	public static final class TopicHandlerExecutor {

		private TopicHandlerExecutor() {
		}

		private static Executor executor;
		
		public static Executor getExecutor(){
			if(executor == null){
				synchronized (TopicHandlerExecutor.class) {
					if(executor == null){
						executor = new Executor(corePoolSize, maxPoolSize, keepAliveTime,cacheSize, "topic-handlerExecutor-prism");
					}
				}
			}
			return executor;
		}
	}

	/**
	 * 
	 * @author pbting
	 *
	 */
	public static final class WatchKeyExecutor {

		private WatchKeyExecutor() {
		}

		private static Executor executor;
		
		public static Executor getExecutor(){
			if(executor == null){
				synchronized (WatchKeyExecutor.class) {
					if(executor == null){
						executor = new Executor(corePoolSize, maxPoolSize, keepAliveTime,cacheSize, "watchKey-executor-prism");
					}
				}
			}
			return executor;
		}
	}
	
}
