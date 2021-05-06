import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationHandler {
    private Properties prop;

    public ConfigurationHandler(String filePath){
        try (InputStream input = ReadExcelData.class.getClassLoader().getResourceAsStream(filePath)) {

           prop = new Properties();

            // load a properties file
            prop.load(input);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDataPath(){
        return prop.getProperty("dataPath");
    }
}
