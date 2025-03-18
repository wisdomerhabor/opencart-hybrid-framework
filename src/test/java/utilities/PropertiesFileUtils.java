package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class PropertiesFileUtils {

	// Method to read properties file and return Properties object
	public Properties readPropertiesFile() throws IOException {
		// Define the file path dynamically
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";

		// Initialize FileInputStream
		FileInputStream fi = new FileInputStream(filePath);

		// Initialize Properties class
		Properties p = new Properties();

		// Load properties file as an object
		p.load(fi);

		// Close FileInputStream
		fi.close();

		// Return properties file object
		return p;
	}

	public static void main(String[] args) throws IOException {
		try {
			// Create an instance of the class
			PropertiesFileUtils pfu = new PropertiesFileUtils();

			// Initailize the read properties method which will return properties file
			// object
			Properties p = pfu.readPropertiesFile();

			// Get values using the returned p object
			String browser = p.getProperty("browser");
			String url = p.getProperty("appURL");

			// Print values
			System.out.println("Browser: " + browser);
			System.out.println("appURL: " + url);

			// *** Reading all keys from the properties file ***
			Set<String> allKeys1 = p.stringPropertyNames();
			System.out.println("The keys in the properties file are : " + allKeys1 + "\t");

			// *** Reading all keys from the properties file 2***
			Set<Object> allKeys2 = p.keySet();
			System.out.println("The keys in the properties file are :: " + allKeys2 + "\t");

			// *** Reading all values from the properties file 2***
			Collection<Object> allValues = p.values();
			System.out.println("The values in the properties file are : " + allValues + "\t");
			
			

		} catch (IOException e) {
			System.out.println("Error reading properties file: " + e.getMessage());
		}
	}
}
