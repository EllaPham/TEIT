/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.actuator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import teit.actuator.model.EnumControl;
import teit.actuator.model.EnumState;
import teit.actuator.model.RangeState;

/**
 *
 * @author Trang
 */
public class RangeGenerator {
    static private String jsonString;
    public static void main(String[] args) throws IOException {
        jsonString = generateSwitchDesciption();
        writeToFile(jsonString); 
        
}
    public static String generateSwitchDesciption() throws JsonProcessingException{
        
        RangeState aRangeState = new RangeState();
        EnumControl aControl = new EnumControl();
       
        List<EnumControl> LControl = new ArrayList<>();
       
       aRangeState.setStartRange(-100);
       aRangeState.setEndRange(100);
       aRangeState.setCurrentState(0);
       aRangeState.setStep(2);
        //Khoi tao cho State      
       
//        //Khoi tao cho Control 1
        aControl.setName("set-value");
        aControl.setStartState("");
        aControl.setEndState("");
        // Khởi tạo cho Control 2
       
        LControl.add(aControl);
       
        aRangeState.setControls(LControl);
        
        ObjectMapper mapper = new ObjectMapper();     
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aRangeState);
        System.out.println(json);
        return json;
    }

    static public void writeToFile(String jsonStr) throws IOException{
        File file = new File("c:/rangeswitch.json");
		

		try (FileOutputStream fop = new FileOutputStream(file)) {

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = jsonStr.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
