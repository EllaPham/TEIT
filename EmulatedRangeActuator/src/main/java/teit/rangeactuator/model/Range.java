/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.rangeactuator.model;

import java.util.List;

/**
 *
 * @author Trang
 */
public class Range {
    public String name;
    public int startRange;
    public int endRange;
    public int currentState;
    public List<Control> controls;
    
    public Control getControlByName(String aName){
        for (Control c: controls){
            if (c.getName().equalsIgnoreCase(aName)){
                return c;
            }          
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }

    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void setcontrols(List<Control> controls) {
        this.controls = controls;
    }
    
    
    public String getName() {
        return name;
    }

    public int getStartRange() {
        return startRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public int getCurrentState() {
        return currentState;
    }

    public List<Control> getcontrols() {
        return controls;
    }
     public int invoke(String actionName, Range Arange, String[] parameter) {
        Control acontrol = Arange.getControlByName(actionName);
        if (acontrol != null) {
            if (acontrol.isIsSet()) {
                currentState = acontrol.getStateValue();
            } else {
                currentState = Arange.getCurrentState() + acontrol.getStateValue(); // cho nay curentState trung voi ARange curentstate
            }

          return currentState;
        }
        return 0;
    }
    
    
}
