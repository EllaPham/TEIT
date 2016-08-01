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

/**
 *
 * @author Trang
 */
public class SmartDoorGenerator {
        static private String jsonStr;
        public static void main(String[] args) throws IOException {
        jsonStr = generateSmartDoorDesciption();
         System.out.println(jsonStr);
        writeToFile2(jsonStr); 
           
    }
          public static String generateSmartDoorDesciption() throws JsonProcessingException{
        
        EnumState aState2 = new EnumState();
        EnumControl aControl = new EnumControl();
        EnumControl aControl2 = new EnumControl();
      
        EnumControl aControl4 = new EnumControl();
        EnumControl aControl5 = new EnumControl();
        
        List<EnumControl> LControl2 = new ArrayList<>();
        List<String> lState2 = new ArrayList<>();
        lState2.add("OPENED");
        lState2.add("CLOSED");
        lState2.add("LOCKED");
        //Khoi tao cho State      
        aState2.setName("door_control");
        aState2.setStateList(lState2);
        //aState.setCurrentState("");
        
        //Khoi tao cho Control 1
        aControl.setName("open-door");
        aControl.setStartState("CLOSED");
        aControl.setEndState("OPENED");
        // Khởi tạo cho Control 2
        aControl2.setName("close-door");
        aControl2.setStartState("OPENED");
        aControl2.setEndState("CLOSED");
        
        
        
        //Khỏi tạo control 4
        aControl4.setName("lock-door");
        aControl4.setStartState("CLOSED");
        aControl4.setEndState("LOCKED");
        
        //Khởi tạo control 5
        aControl5.setName("unlock-door");
        aControl5.setStartState("LOCKED");
        aControl5.setEndState("CLOSED");
        
        //LControl list add
        LControl2.add(aControl);
        LControl2.add(aControl2);       
        LControl2.add(aControl4);
        LControl2.add(aControl5);
        aState2.setControlList(LControl2);
        
        ObjectMapper mapper = new ObjectMapper();     

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aState2);
        System.out.println(json);
        return json;
    }

    static public void writeToFile2(String jsonStr) throws IOException{
        File file = new File("c:/DoorSwitch.json");
		

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
