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
 * @author trang
 */
public class Main {

    static private EnumControl enumControl = new EnumControl();
    static private String currentState;
    static private List<String> stateList;
    static private List<EnumControl> controlList;
    static private List<String> actionList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        String jsonString;
        
        if (args.length > 0) {
            EnumState enumState;
            ObjectMapper mapper = new ObjectMapper();
            enumState = mapper.readValue(new File("actuator.data"), EnumState.class);
            currentState = getState(enumState).get(0);//???
            switch (args[0]) {
                case "state-list":                   
                    System.out.println(getState(enumState));
                    break;
                case "current-state":
                   
                    System.out.println(enumState.getCurrentState());
                    break;
                case "action-list":
                    
                    System.out.println(getActionList(enumState.getControls()));
                    break;
              default: // day nay. Ngoai 3 lenh mac dinh o tren. Doan default nay lay ra action name
                   
                    if (enumState.getCurrentState()==null){
                        System.out.println("Unknown command !");
                        return;
                    }
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("actuator.data"), enumState);
                    break;
                
            }
            
            return;
        } else {
            jsonString = SwitchGenerator.generateSwitchDesciption();
            ObjectMapper mapper = new ObjectMapper();
            EnumState enumState = mapper.readValue(jsonString, EnumState.class);
            stateList = getState(enumState);
            controlList = getControl(enumState);
            currentState = stateList.get(0);
            currentState = getCurrentState(enumControl);
            actionList = getActionList(controlList);

        }

        System.out.println("CURRENT State: " + currentState);
        System.out.println("State LIST: " + stateList);
        System.out.println("Current State UPDATED:" + currentState);

    }

    static public List<String> getState(EnumState eState) {

        return eState.getStateList();
    }

    static public List<EnumControl> getControl(EnumState eState) {
        return eState.getControlList();
    }

    static public String getCurrentState(EnumControl aControl) {
        return aControl.getEndState();
    }

    static public List<String> getActionList(List<EnumControl> ctrlList) {
        List<String> actions = new ArrayList<>();
        for (EnumControl aCtrl : ctrlList) {

            actions.add(aCtrl.getName());
        }
        return actions;
    }
}
