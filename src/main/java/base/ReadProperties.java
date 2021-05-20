package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    public static String getProperty(String filename, String propname) throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream("./target/test-classes/properties/"+filename+".properties"));
        String str=properties.getProperty(propname);
        return  str;
    }
}
