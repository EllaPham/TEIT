/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.actuator.Main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import teit.actuator.model.EnumState;
import teit.actuator.model.EnumControl;

/**
 *
 * @author hungld
 */
public class SamplesGenerator {
static private String jsonString;
//    public static void main(String[] args) throws IOException {
//        jsonString = generateSwitchDesciption();
//        writeToFile(jsonString); 
//        
//
//    }
    
    public static String generateSwitchDesciption() throws JsonProcessingException{
        
        EnumState aState = new EnumState();
        EnumControl aControl = new EnumControl();
        EnumControl aControl2 = new EnumControl();

        List<EnumControl> LControl = new ArrayList<>();
        List<String> lState = new ArrayList<>();
        lState.add("ON");
        lState.add("OFF");
        lState.add("STUCK");
        //Khoi tao cho State      
        aState.setDescription("Switch");
        aState.setStateList(lState);
        //Khoi tao cho Control 1
        aControl.setName("turn-on");
        aControl.setStartState("OFF");
        aControl.setEndState("ON");
        // Khởi tạo cho Control 2
        aControl2.setName("turn-off");
        aControl2.setStartState("ON");
        aControl2.setEndState("OFF");
        //LControl list add
        LControl.add(aControl);
        LControl.add(aControl2);
        aState.setControlList(LControl);
        
        ObjectMapper mapper = new ObjectMapper();     
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aState);
        System.out.println(json);
        return json;
    }

    static public void writeToFile(String jsonStr) throws IOException{
        File file = new File("c:/description.json");
		

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
	
    


