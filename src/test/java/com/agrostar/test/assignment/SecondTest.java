package com.agrostar.test.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SecondTest {

	private ArrayList<Character> charList = new   ArrayList<Character>();
	private Map<Character,Integer> operatorMap = new HashMap<Character, Integer>();
	
	/**
	 * Validate balanced pair of parentheses in expression
	 */
	
	@Test(description="Validate balanced pair of parentheses in expression",alwaysRun=true,dataProvider="expressionDataProvider")
	public void balancePair(String sample , String str){
		
		if(!str.isEmpty() && str != null){
			if( !str.trim().equals("(") && !str.trim().equals(")")){
				
				char[] chars = str.toCharArray();
				
				for(char c: chars){
					if(!Character.isLetterOrDigit(c) && c == '('  || c == ')') {
						charList.add(c);
					}
				}
				
				for(Character ele: charList){					
					if(operatorMap.get(ele) != null && operatorMap.get(ele) != 0){						
						operatorMap.put(ele,operatorMap.get(ele)+1);
					}else{
						operatorMap.put(ele,1);
					}
				}
				
				for(Map.Entry<Character, Integer> ele: operatorMap.entrySet()){
					System.out.println(String.format("Brace Type \"%s\" and count is \"%d\" ", ele.getKey(),ele.getValue()));					
				}
				
				if(operatorMap.get('(') == operatorMap.get(')')){
					System.out.println("**** Valid Expression ****");
				}else{
					System.out.println("**** Invalid Expression ****");
				}
				
			}else{
				System.out.println("**** Incomplete expression to validate ****");
			}
			
		}else{
			System.out.println("**** Expression is  empty or null ****");
		}		
	}
	
	@DataProvider(name="expressionDataProvider")
	public Object[][] dataProvider(){
		return new Object[][] { {"Sample-1" ,"(())"}, {"Sample-2" ,"((a+b)+(c*d))"}, {"Sample-3" ,"((a*b)/(c+d)"}};
		
	}
	
}
