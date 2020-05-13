package learnJava;

import java.util.Iterator;

public class Palindrome2 {

	public static void main(String[] args) {

		String inputString = "malayalam";
		String reverseString = "";
		
		for (int i = inputString.length()-1;i>=0;i--)
		{
			reverseString += inputString.charAt(i);
		}
		
		System.out.println(inputString.equals(reverseString)? "Yes,Palindrome" :"Not a Palindrome" );
	}
}

