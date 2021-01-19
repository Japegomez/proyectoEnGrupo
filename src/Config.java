import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	Properties prop=new Properties();
	
	public Config() {
		FileInputStream ip;
		try {
			ip = new FileInputStream("config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getProp() {
		return prop;
	}
}
