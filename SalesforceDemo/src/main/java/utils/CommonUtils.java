package utils;

import java.util.Date;
import java.util.Random;

public class CommonUtils {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=15;
	public static final int EXPLICIT_WAIT_BASIC_TIME=30;
	
	public String getEmailWithTimeStamp() 
	{
		
		Date date = new Date();
		return "abhishek"+date.toString().replace(" ","_").replace(":","_")+"@gmail.com";
		
	}

	
	// Method to generate a random 10-digit mobile number
    public static String generateRandomMobileNumber() 
    {
        Random random = new Random();
        
        // Mobile numbers typically start with 6, 7, 8, or 9
        int firstDigit = 6 + random.nextInt(4); // Generates 6, 7, 8, or 9
        
        // Generate remaining 9 digits
        long remainingDigits = (long) (random.nextDouble() * 1_000_000_000L);
        
        return firstDigit + String.format("%09d", remainingDigits);
    }
    
  
}














