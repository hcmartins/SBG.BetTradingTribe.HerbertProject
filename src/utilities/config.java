package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {
	public static String getproperty(String RowName) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			System.out
					.println("Please provide the path of Config.properties file in src/test/java/utilites/config.java");
			e.printStackTrace();
		}
		return prop.getProperty(RowName);

	}
}
