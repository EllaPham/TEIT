/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.actuator;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import teit.actuator.model.EnumControl;
import teit.actuator.model.EnumState;

/**
 *
 * @author hungld
 */
public class Main {
    static private EnumState enumState = new EnumState();
    static private EnumControl enumControl = new EnumControl();
    static private String currentState;
    static private List<String> stateList;
    static private List<EnumControl> controlList;
      public static void main(String[] args) throws  IOException{
            String jsonString;
          //convert json string to object java
          if (args.length > 0){
              enumState = getJsonString(args[0]);
          }
          else{
                jsonString = SamplesGenerator.generateSwitchDesciption();
                ObjectMapper mapper = new ObjectMapper();
                EnumState enumState = mapper.readValue(jsonString, EnumState.class);
          }
        
          	
                stateList = getState(enumState);
                controlList = getControl(enumState);
                currentState = stateList.get(0);
                System.out.println("CURRENT State: " + currentState);
                System.out.println("State LIST: " +stateList );
               
                enumControl = invoke("turn-off",enumState,null);
                currentState =getCurrentState(enumControl);
                System.out.println("Current State UPDATED:" + currentState);
      }
    static public List<String> getState(EnumState eState){
        
        return eState.getStateList();
    }
    static public List<EnumControl> getControl(EnumState eState){
       return eState.getControlList(); 
    }
    static public String getCurrentState(EnumControl aControl){
        return aControl.getEndState();
    }
    public static EnumControl invoke(String actionName,EnumState eState, String[] parameter){
        List<EnumControl> controlList= new ArrayList<>();
        EnumControl control= new EnumControl();
        controlList = eState.getControlList();
        for (EnumControl aControl: controlList){
            if (aControl.getName().equalsIgnoreCase(actionName)){
                  
                control = aControl; 
            }
           
        }    
        return control; 
    }
    static public EnumState getJsonString(String filePath) throws IOException{
        
        EnumState eState = new EnumState();
        ObjectMapper mapper = new ObjectMapper();
       

        //Object to JSON in file
         mapper.writeValue(new File(filePath), eState);

        return eState;
    }
}
