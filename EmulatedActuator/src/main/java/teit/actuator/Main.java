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

        //convert json string to object java
        if (args.length > 0) {
            EnumState enumState;
//        ObjectMapper mapper = new ObjectMapper();
            ObjectMapper mapper = new ObjectMapper();
            enumState = mapper.readValue(new File("actuator.data"), EnumState.class);
            currentState = getState(enumState).get(0);//???
            switch (args[0]) {
                case "state-list":
                    stateList = getState(enumState);
                    System.out.println(stateList);
                    break;
                case "current-state":
                    currentState = enumState.getCurrentState();
                    System.out.println(currentState);
                    break;
                case "action-list":
                    actionList = getActionList(enumState.getControls());
                    System.out.println(actionList);
                    break;
              default:
                    enumState = invoke(args[0], enumState, null);
                    if (enumState==null){
                        System.out.println("Unknown command !");
                        return;
                    }
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("actuator.data"), enumState);
                    break;
                
            }
            return;
        } else {
            jsonString = SamplesGenerator.generateSwitchDesciption();
            ObjectMapper mapper = new ObjectMapper();
            EnumState enumState = mapper.readValue(jsonString, EnumState.class);
            stateList = getState(enumState);
            controlList = getControl(enumState);
            currentState = stateList.get(0);
            currentState = getCurrentState(enumControl);
            actionList = getActionList(controlList);
//=======
//        EnumState enumState;
//        ObjectMapper mapper = new ObjectMapper();
//        
//        if (args.length > 0) {
//            enumState = mapper.readValue(new File(args[0]), EnumState.class);
//        } else {
//            String jsonString = SamplesGenerator.generateSwitchDesciption();
//            enumState = mapper.readValue(jsonString, EnumState.class);
//>>>>>>> 68826ba8e95bcfb4dfc64702ea1ba83305c8fbe7
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

    public static EnumState invoke(String actionName, EnumState eState, String[] parameter) {
        List<EnumControl> controlLst = new ArrayList<>();
        EnumControl control = new EnumControl();
        controlLst = eState.getControlList();
        for (EnumControl aControl : controlLst) {
            if (aControl.getName().equalsIgnoreCase(actionName)) {
                eState.setCurrentState(aControl.getEndState());
                // tim thay la return luon
                return eState;
            }

        }
        // return null tuc la khong tim thay action trung ten
        return null;
    }

}
