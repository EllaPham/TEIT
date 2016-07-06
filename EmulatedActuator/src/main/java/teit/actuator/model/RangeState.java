/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.actuator.model;

import java.util.List;

/**
 *
 * @author Trang
 */
public class RangeState {
    private int startRange;
    private int endRange;
    private int step;
    private int currentState;
   private List<EnumControl> controls;
    
    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }

    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void setControls(List<EnumControl> controls) {
        this.controls = controls;
    }

    public int getStartRange() {
        return startRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public int getStep() {
        return step;
    }

    public int getCurrentState() {
        return currentState;
    }

    public List<EnumControl> getControls() {
        return controls;
    }
    
    
}
