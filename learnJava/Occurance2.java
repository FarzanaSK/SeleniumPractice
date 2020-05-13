package learnJava;

public class Occurance2 {

	public static void main(String[] args) {

		String inputString = "You have no choice other than following me";
		char[] inputArray = inputString.toCharArray();
		int count = 0;
		for (char c : inputArray) 
		{
			if (c=='o') 
			{
				count++;
				
			}	
		}
		System.out.println("Occurance of o is : "+count);
		
	}

}
