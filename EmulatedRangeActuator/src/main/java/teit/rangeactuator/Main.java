/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.rangeactuator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import teit.rangeactuator.model.Control;
import teit.rangeactuator.model.Range;

/**
 *
 * @author Trang
 */
public class Main {
    static public int currentState;
   public static void main(String[] args) throws IOException{
      
        if (args.length > 0) {
            
            Range range;
            ObjectMapper mapper = new ObjectMapper();
            range = mapper.readValue(new File("RangeActuator.data"), Range.class);
               currentState = range.getCurrentState();//current-state = 16
             switch (args[0]) {
                 case "current-state":                 
                     System.out.println(range.getCurrentState());
                    break;
                case "reduce-1": 
                    currentState= currentState - 1;
                    range.setCurrentState(currentState);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("RangeActuator.data"), range);
                 
                    break;
                case "increase-1":
                    currentState= currentState + 1;
                    range.setCurrentState(currentState);   
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("RangeActuator.data"), range);
                    break;
                case "set-default":
                  range = invoke(args[0], range, null);//range.getcurrentState() = 20
                  currentState = range.getCurrentState();                                                      
                  mapper.writerWithDefaultPrettyPrinter().writeValue(new File("RangeActuator.data"), range);
                    break;
             }
          
       }
        else {
       }
   }
   public static Range invoke(String actionName, Range Arange, String[] parameter) {
     
        List<Control> controlLst = new ArrayList<>();
        Control acontrol = new Control();
        controlLst = Arange.getcontrols();
        for (Control aControl : controlLst) {
            if (aControl.getName().equalsIgnoreCase(actionName)) {
               
                Arange.setCurrentState(aControl.getStateValue());              
           return Arange;
            }

        }        
        return null;
    }
}

