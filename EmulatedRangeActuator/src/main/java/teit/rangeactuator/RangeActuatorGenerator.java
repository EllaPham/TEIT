/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.rangeactuator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import teit.rangeactuator.model.Control;
import teit.rangeactuator.model.RangeActuator;

/**
 *
 * @author Trang
 */
public class RangeActuatorGenerator {
    static private String jsonString;
    public static void main(String[] args) throws IOException {
        jsonString = generateRangeDesciption();
        writeToFile(jsonString); 
}

    private static String generateRangeDesciption() throws JsonProcessingException {
        RangeActuator aRange = new RangeActuator();
        Control aControl1 = new Control();
        Control aControl2 = new Control();
        Control aControl3 = new Control();
        Control aControl4 = new Control();
        Control aControl5 = new Control();
        
        List<Control> LControl = new ArrayList<>();
        
       
        //Khoi tao cho State      
        aRange.setName("Range");
        aRange.setStartRange(1);
        aRange.setEndRange(100);
        aRange.setCurrentState(16);
        
        //Khoi tao cho Control 1
        aControl1.setName("set-default");
        aControl1.setIsSet(true);
        aControl1.setStateValue(50);
        // Khởi tạo cho Control 2
        aControl2.setName("set-high");
        aControl2.setIsSet(true);
        aControl2.setStateValue(80);
        // Khởi tạo control 3
        aControl3.setName("set-low");
        aControl3.setIsSet(true);
        aControl3.setStateValue(10);
        //Khoi tao control 4l
        aControl4.setName("tomtom");
        aControl4.setIsSet(false);
        aControl4.setStateValue(5);
        //Khoi tao control 5
        aControl5.setName("bitter");
        aControl5.setIsSet(false);
        aControl5.setStateValue(-1);
        //LControl list add
        LControl.add(aControl1);
        LControl.add(aControl2);
        LControl.add(aControl3);
        LControl.add(aControl4);
        LControl.add(aControl5);
        aRange.setcontrols(LControl);
        
        ObjectMapper mapper = new ObjectMapper();     
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aRange);
        System.out.println(json);
        return json;
    }
    static public void writeToFile(String jsonStr) throws IOException{
        File file = new File("c:/RangeDescription.json");
		

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
