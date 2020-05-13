package learnJava;

public class Palindrome {

	public static void main(String[] args) {

		String inputString = "malayalam";
		StringBuffer StrBuf = new StringBuffer(inputString);
		StringBuffer RevBuf = StrBuf.reverse();
		String ReverseString = RevBuf.toString();
		
		if(ReverseString.equals(inputString))
		{
			System.out.println("It is a Palindrome");
		}
		else
		{
			System.out.println("Not a Palindrome");
		}
		
		
        		
	}
}
