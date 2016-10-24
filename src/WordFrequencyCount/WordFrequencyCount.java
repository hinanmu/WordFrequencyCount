package WordFrequencyCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.management.StringValueExp;


public class WordFrequencyCount {

	static Map<String, Integer> map1 = new HashMap<String, Integer>();//´æ´¢Î´ÅÅÐòµÄ´ÊÆµ
	static Map<String, Integer> map2 = new HashMap<String, Integer>();
	static Map<String, Integer> map3 = new HashMap<String, Integer>();
	
	static List<Map.Entry<String, Integer>> orderList1;//´æ´¢´ÊÆµ·½±ãÅÅÐò
	static List<Map.Entry<String, Integer>> orderList2;
	static List<Map.Entry<String, Integer>> orderList3;
	
	 public static void main(String[] args) throws IOException 
	 {
		 FileReader fileReader = new FileReader("Ci.txt");
		 BufferedReader bufferedReader = new BufferedReader(fileReader);  
		 String line = bufferedReader.readLine();
		 while(line != null)
		 {
			 dealIt(line);
			 line = bufferedReader.readLine();
		 }
		 bufferedReader.close();
		 fileReader.close();
		 
		orderList1 = sortByValue(map1);
		orderList2 = sortByValue(map2);
		orderList3 = sortByValue(map3);
		
		output(orderList1, "count1.txt");
		output(orderList2, "count2.txt");
		output(orderList3, "count3.txt");
		
		FileWriter createPoem = new FileWriter(new File("poem.txt"));
		createPoem.write("¾ÆÈª×Ó\n");
	
		int[] pattern = {2,2,0,2,2,3,-1,2,2,3,-1,2,1,2,-1,2,2,3,-1,2,2,3,-1,2,2,3,-1,2,1,2,-1};
		
		for(int i = 0; i < pattern.length; i++)
		{
			switch (pattern[i]) {
			case -1:
				createPoem.write("¡£");
				break;
			case 0:
				createPoem.write(",");
				break;
			case 1:
				int random1 = (int)(0+Math.random()*(orderList1.size()-0+1))/100;
				createPoem.write(orderList1.get(random1).getKey());
				break;
			case 2:
				int random2 = (int)(0+Math.random()*(orderList2.size()-0+1))/1000;
				createPoem.write(orderList2.get(random2).getKey());
				break;
			case 3:
				int random3 = (int)(0+Math.random()*(orderList3.size()-0+1))/10000;
				createPoem.write(orderList3.get(random3).getKey());
				break;
			default:
				break;
			}
		}
		createPoem.close();
		
		 
		 
	 }
	 
	 public static boolean isChinese(char x)
	 {
		 return (x >= '\u4e00' && x <= '\u9fa5') ? true : false;
	 }
	 
	 public static void dealIt(String line)
	 {
		if(line.length() > 5)
		{
			for(int i = 0; i < line.length(); i++)
			{
				char c = line.charAt(i);
				if(isChinese(c))
				{
					if(map1.containsKey(String.valueOf(c)))
					{
						map1.put(String.valueOf(c), map1.get(String.valueOf(c)).intValue()+1);
					}
					else
					{
						map1.put(String.valueOf(c), 1);
					}
				}
			}
			
			for(int i = 0; i < line.length()-1; i++)
			{
				String s = line.substring(i, i+2);
				char temp1 = s.charAt(0),temp2 = s.charAt(1);
				if(isChinese(temp1) && isChinese(temp2))
				{
					if(map2.containsKey(s))
					{
						map2.put(s, map2.get(s).intValue()+1);
					}
					else
					{
						map2.put(s, 1);
					}
				}
			}
			
			for(int i = 0;i < line.length()-2; i++)
			{
				String s = line.substring(i,i+3);
				char temp1=s.charAt(0),temp2=s.charAt(1),temp3=s.charAt(2);
				if(isChinese(temp1) && isChinese(temp2) && isChinese(temp3))
				{
					if(map3.containsKey(s))
					{
						map3.put(s, map3.get(s).intValue()+1);
					}
					else
					{
						map3.put(s, 1);
					}
				}
			}
		}
	}
	 
	 public static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> map)
	 {
			List<Map.Entry<String, Integer>> orderList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
			Collections.sort(orderList, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			return orderList;
		}
	 
	 public static void output(List<Map.Entry<String, Integer>> list, String filename) throws IOException {
			FileWriter writer = new FileWriter(new File(filename));
			for (Entry<String, Integer> mapping : list) {
				writer.write(mapping.getKey() + " " + mapping.getValue() + "\r\n");
			}
			writer.close();

		}
	
}
