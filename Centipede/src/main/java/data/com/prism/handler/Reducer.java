package data.com.prism.handler;

public interface Reducer<K,V> {

	public void reduce(K k,V v);
}
