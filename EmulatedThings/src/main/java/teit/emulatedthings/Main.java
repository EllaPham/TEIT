/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedthings;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import teit.actuator.model.EnumState;
import teit.rangeactuator.model.Range;

/**
 *
 * @author Trang
 */
public class Main {
     public static void main(String[] args) throws IOException {
        // String enumCurrentState;
         Map<String,EnumState> enumMap = new HashMap<>();
         Map<String,Range> rangeMap = new HashMap<>();
         ObjectMapper mapper = new ObjectMapper();
       
          Range rangeLevel;
          ObjectMapper mapperLevel = new ObjectMapper();
          rangeLevel = mapperLevel.readValue(new File("levelDescription.data.json"), Range.class);
          
          Range rangeColor;
          ObjectMapper mapperColor = new ObjectMapper();
          rangeColor = mapperColor.readValue(new File("colorDescription.data.json"), Range.class);
          
          EnumState onOff;
          ObjectMapper mapperOnOff = new ObjectMapper();
          onOff = mapperOnOff.readValue(new File("OnOff.data.json"), EnumState.class);
          
          enumMap.put(onOff.getName(), onOff);
          rangeMap.put(rangeLevel.getName(), rangeLevel);
          rangeMap.put(rangeColor.getName(), rangeColor);
                 
         String[] fullName= args[0].split("\\.");
         //System.out.println("ARG 0 : " + args[0]);
         //System.out.println("THE LENGTH IS: " + fullName.length);
         if(fullName.length <2){System.out.println("Wrong Command"); return;}
         String actuatorName = fullName[0];
         String actionName = fullName[1];
        
     
         String temp;
         int count = 0;
         for(String key: enumMap.keySet()){
             temp = enumMap.get(key).getName();
             //enumCurrentState = enumMap.get(key).getCurrentState();
             if(temp.equals(actuatorName)){
                 count++;
                 switch(actionName.trim()) {
                     case "current-state":
                         System.out.println(enumMap.get(key).getCurrentState());
                         break;
                     case "state-list":
                         System.out.println(enumMap.get(key).getStateList());
                         break;
                     default:
                        String stateX = enumMap.get(key).invoke(actionName, null);
                        System.out.println("STATE X: " + stateX);
                        if (stateX == null) {
                            System.out.println("Unknown command of actuator " + key );
                        }
                        mapperOnOff.writerWithDefaultPrettyPrinter().writeValue(new File("OnOff.data.json"), enumMap.get(key));
                        break;
                   
                 }
                
                
             }
         }
             
         for (String k: rangeMap.keySet()){
            
             temp = rangeMap.get(k).getName();
             if(temp.equals(actuatorName)){
                  count++;
                 switch(actionName.trim()){
                     case "current-state":
                         System.out.println(rangeMap.get(k).getCurrentState());
                         break;
                     default:
                         int stateY = rangeMap.get(k).invoke(actionName, rangeMap.get(k), args);
                         if(stateY == 0){
                             System.out.println("Unknown command of actuator" + k);
                         }
                 }
                 rangeMap.get(k).invoke(actionName, rangeMap.get(k), null);
                
             }
         }
         if(count==0){
             System.out.println("Not found actuator:" + actuatorName);
         }  
         
          
     }
}
