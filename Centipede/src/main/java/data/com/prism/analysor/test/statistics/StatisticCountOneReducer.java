package data.com.prism.analysor.test.statistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import data.com.prism.Log;
import data.com.prism.handler.Reducer;

//@TopicReduce(topic="countOne")
public class StatisticCountOneReducer implements Reducer<Integer,List<Integer>> {
	public void reduce(Integer k, List<Integer> v) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("D:/out_count.txt",true));){
			writer.write(k+"----reducer----"+v.get(0));
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			Log.error("", e);
		}
		
		int memoryCount = CountHouseMgr.add("countOne",k, v.get(0));
		System.out.println(k+"-->memory count:"+memoryCount);
		int diskCount = CountHouseMgr.getPersisterCountMap().get(k);
		System.out.println(k+"-->disk count:"+diskCount);
		System.out.println("<------------------------->");
	}
}
