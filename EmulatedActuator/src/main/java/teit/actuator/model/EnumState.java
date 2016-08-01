/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.actuator.model;

import java.util.List;

/**
 *
 * @author trang
 */
public class EnumState {

    private String name;
    private List<String> states;
    private List<EnumControl> controls;
    private String currentState;

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public List<String> getStates() {
        return states;
    }

    public List<EnumControl> getControls() {
        return controls;
    }
    
    

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStateList(List<String> stateList) {
        this.states = stateList;
    }

    public List<String> getStateList() {
        return states;
    }

    public List<EnumControl> getControlList() {
        return controls;
    }

    public void setControlList(List<EnumControl> controlList) {
        this.controls = controlList;
    }
     public String invoke(String actionName,String[] parameter) {
     
        for (EnumControl aControl : controls) {
            if (aControl.getName().equalsIgnoreCase(actionName)) {
               
                this.currentState = aControl.getEndState();
                System.out.println("INVOKE CURRENT STATE=== " + currentState);
               return currentState; 
               
            }
        }    
         System.out.println("Return null wrong action");
        return null;
    }

}
