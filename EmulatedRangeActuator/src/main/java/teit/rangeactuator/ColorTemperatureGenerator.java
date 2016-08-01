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
import teit.rangeactuator.model.Range;

/**
 *
 * @author Trang
 */
public class ColorTemperatureGenerator {
      static private String jsonString;
    public static void main(String[] args) throws IOException {
        jsonString = generateColorDesciption();
        writeToFile(jsonString); 
}
      public static String generateColorDesciption() throws JsonProcessingException {
        Range aRange = new Range();
        Control aControl1 = new Control();
        Control aControl2 = new Control();
        Control aControl3 = new Control();
        Control aControl4 = new Control();
        
        
        List<Control> LControl = new ArrayList<>();
        
       
        //Khoi tao cho State      
        aRange.setName("color");
        aRange.setStartRange(2700);
        aRange.setEndRange(5500);
        aRange.setCurrentState(3000);
        
        //Khoi tao cho Control 1
        aControl1.setName("set-default");
        aControl1.setIsSet(true);
        aControl1.setStateValue(3400);
        // Khởi tạo cho Control 2
        aControl2.setName("set-transition-time");
        aControl2.setIsSet(true);
        aControl2.setStateValue(10);
        // Khởi tạo control 3
        aControl3.setName("set-stepsize");
        aControl3.setIsSet(true);
        aControl3.setStateValue(45);
        //Khoi tao control 4l
        aControl4.setName("set-rate");
        aControl4.setIsSet(false);
        aControl4.setStateValue(20);

        //LControl list add
        LControl.add(aControl1);
        LControl.add(aControl2);
        LControl.add(aControl3);
        LControl.add(aControl4);
        //LControl.add(aControl5);
        aRange.setcontrols(LControl);
        
        ObjectMapper mapper = new ObjectMapper();     
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aRange);
        System.out.println(json);
        return json;
    }
    static public void writeToFile(String jsonStr) throws IOException{
        File file = new File("c:/colorDescription.json");
		

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



