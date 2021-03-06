package data.com.prism.analysor.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.com.prism.handler.TopicMap;

//@TopicHandler(fileName="",topic="topicOne")
public class TopicOneMapper extends TopicMap<String,Person> {

	@Override
	public void map(String line, Map<String, List<Person>> logContext, boolean hasNextToken) {
		System.out.println(line);
		String[] values = line.split("\\|");
		if(values.length == 1){
			String luckyNum = values[0];
//			System.out.println("index -->"+values[3]);
//			if(luckyNum.indexOf("6")>=0||luckyNum.indexOf("8")>=0){
			if(true){
				Person person = new Person(Integer.valueOf(values[0]), values[1], values[2], Integer.valueOf(values[3]));
				if(logContext.containsKey(luckyNum)){
					logContext.get(luckyNum).add(person);
				}else{
					List<Person> pers = new ArrayList<Person>();
					pers.add(person);
					logContext.put(luckyNum, pers);
				}
			}
		}
	}
}
