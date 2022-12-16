import org.junit.Test;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions;


public class InputTest {
    ObjectMapper mapper = new ObjectMapper();
    File inputJsonFile = new File("input.json");
    Input input = ObjectMapper.readValue(inputJsonFile,Input.class);

    InputTest()throws IOException{
    }

    @Test
    void testgetFFRate(){
        int expectedFFRate = 15;
        assertEquals(expectedFFRate,input.getFFRate());
    }

    @Test
    void testgetMaxNumOfSelectionForCourses(){
        int expectedgetMaxNumOfSelectionForCourses = 10;
        assertEquals(expectedgetMaxNumOfSelectionForCourses,input.getMaxNumOfSelectionForCourses());
    }

    @Test
    void testStartSimulation(){}




}
