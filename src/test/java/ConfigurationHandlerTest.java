import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationHandlerTest {
    ConfigurationHandler configurationHandler;

    @BeforeEach
    void setup(){
        configurationHandler = new ConfigurationHandler("testConfig.properties");
    }


    @Test
    void should_returnPathString_whenPropFileCorrect(){
        //given
        String expectedPath = "testfolder/";

        //when
        String actualPath = configurationHandler.getDataPath();

        //then
        Assertions.assertEquals(expectedPath, actualPath);
    }


}