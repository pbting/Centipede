package data.com.prism.creator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class CreateLuckyNumber {
	private static final Random RANDOM = new Random();
	public static void main(String[] args) throws Exception {
		System.out.println(randomRound(65, 90,8));
		int count = 10000;
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\_文档\\demo.txt")));
		while(count-->0){
			writer.write(randomRound(65, 90, 8));
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}
	
	public static int randomRound(int min,int max){
		return RANDOM.nextInt(max)%(max-min+1) + min;
	}
	
	/**
	 * 
	 * @param min
	 * @param max
	 * @param count
	 * @return
	 */
	public static String randomRound(int min,int max,int count){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<count;i++){
			int num = RANDOM.nextInt(max)%(max-min+1) + min;
			char ch = (char)(num);
			sb.append(ch).append(",");
		}
		
		return sb.deleteCharAt(sb.length()-1).toString();
	}
}
