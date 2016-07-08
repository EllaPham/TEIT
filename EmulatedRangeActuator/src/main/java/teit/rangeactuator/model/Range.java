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
    public String description;
    public int startRange;
    public int endRange;
    public int currentState;
    public List<Control> controls;

    public void setDescription(String description) {
        this.description = description;
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
    
    
    public String getDescription() {
        return description;
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
    
    
}
