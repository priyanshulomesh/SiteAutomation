package MyUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig{
	Properties pro;
	
	public ReadConfig(){
	
		File src=new File("./Configurations/config.properties");
		try {
			FileInputStream fls=new FileInputStream(src);
				pro =new Properties();
				pro.load(fls);
	} catch(Exception e) {
				System.out.println("Exception is: "+e.getMessage());
			}
	}
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseUrl");
		return url;
	}
	
	public String getUserName()
	{
		String un=pro.getProperty("username");
		return un;
	}
	
	public String getPassword()
	{
		String pw=pro.getProperty("password");
		return pw;
	}
}
