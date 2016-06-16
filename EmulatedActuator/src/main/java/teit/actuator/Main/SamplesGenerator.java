/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.actuator.Main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jdk.internal.org.objectweb.asm.TypeReference;
import teit.actuator.model.EnumState;
import teit.actuator.model.EnumControl;

/**
 *
 * @author hungld
 */
public class SamplesGenerator {

    public static void main(String[] args) throws IOException {
        generateSwitchDesciption();
        
        

    }
    
    private static void generateSwitchDesciption() throws JsonProcessingException{
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
        aControl.setName("turn-off");
        aControl.setStartState("ON");
        aControl.setEndState("OFF");
        //LControl list add
        LControl.add(aControl);
        LControl.add(aControl2);
        aState.setControlList(LControl);
        
        ObjectMapper mapper = new ObjectMapper();     
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aState);
        System.out.println(json);
    }

    static public EnumState convertJsonToJava(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();        
        return mapper.readValue(new File(filePath), EnumState.class);     
    }

}
