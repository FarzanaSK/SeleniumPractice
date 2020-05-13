package learnJava;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Index;

public class OccuranceLearn {

	public static void main(String[] args)
	{

		String inputString = "You have no choice other than following me";

		//Creating Hashmap with Character as Key and its Count as Value
        Map<Character, Integer> ChkOccuranceMap = new HashMap<Character, Integer>(); 
 
       // Converting given string to char array 
 
       char[] strArray = inputString.toCharArray(); 
 
       // checking each char of strArray 
       for (char c : strArray) 
       { 
    	  if (c == 'o')
    	  {
    		  if (ChkOccuranceMap.containsKey(c)) 
    		  { 
    			  // If "o" is present in ChkOccuranceMap then increment the count by 1
    			  ChkOccuranceMap.put(c, ChkOccuranceMap.get(c) + 1); 
    		  } 
    		  else 
    		  { 
               // If it is not present in Map putting the char to ChkOccuranceMap with 1 as it's value 
    			  ChkOccuranceMap.put(c, 1); 
    		  } 
    	  } 
    	  
       }
 
       // Printing the Map 
       for (Map.Entry entry : ChkOccuranceMap.entrySet()) 
       {   
          System.out.println("Occurance of "+entry.getKey() + "is " + entry.getValue());
    	   
       } 		
	}	
}

