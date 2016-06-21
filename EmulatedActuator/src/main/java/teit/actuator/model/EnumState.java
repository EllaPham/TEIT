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

    private String description;
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
    
    

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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

}
