package com.agrostar.test.assignment;

import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstTest {

	private Map<Character, Integer> charMap= new TreeMap<Character,Integer>();
	
	/**
	 * Print charter with count of occurrences in given string 
	 */
	@Test(description="Print charter with count of occurrences in given string",
			alwaysRun=true, 
			dataProvider="charterCounterProvider")
	public void  processChaOccurrenceCount(String sample,String str){
		
		char[] charArr = str.trim().toUpperCase().toCharArray();
		
		if(!str.isEmpty()){
			for(char c: charArr){
				if(Character.isLetter(c)){
					if(charMap.get(Character.valueOf(c)) != null){
						Integer count = charMap.get(Character.valueOf(c));
						charMap.put(Character.valueOf(c), count+1);
					}else{
						charMap.put(Character.valueOf(c), 1);
					}					
				}
			}
			
			for(Map.Entry<Character, Integer> element : charMap.entrySet()){
				System.out.println(String.format("%s is occured %d times", Character.toUpperCase(element.getKey()),element.getValue()));								
			}
			
		}else{
			System.out.println("String not defined or Empty");
		}
	}
	
	@DataProvider(name="charterCounterProvider")
	public Object[][] stringDataProvider(){
		return new Object[][] { {"Sample-1" ,"xYxcvbBV"}, {"Sample-2" ,"(aBcAabbCD"}, {"Sample-3" ,"#$%#sfsdfsDD{}"}};
	}
}
