package utilities;

import org.apache.commons.lang.RandomStringUtils;

public class RandomUtils {

	public String randomString() {

		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomPhoneNumber() {

		String generatedNumber = RandomStringUtils.randomNumeric(8);
		String phoneNumber = "080"+generatedNumber;
		return phoneNumber;
	}
	
	public String randomNumber() {
		
		String generatedNumber = RandomStringUtils.randomNumeric(11);
		return generatedNumber;
	}
	
	public String randomAphanumeric() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return generatedString+generatedNumber;
	}
	
	public String randomAphanumericwithSpecialChar() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(2);
		return generatedString+generatedNumber+"@*";
	}
}
