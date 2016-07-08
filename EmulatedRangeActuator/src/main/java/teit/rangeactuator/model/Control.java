/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.rangeactuator.model;

/**
 *
 * @author Trang
 */
public class Control {
    public String name;
    public int stateValue;
    public boolean isSet;

    public void setName(String name) {
        this.name = name;
    }

    public void setStateValue(int stateValue) {
        this.stateValue = stateValue;
    }

    public void setIsSet(boolean isSet) {
        this.isSet = isSet;
    }

    public String getName() {
        return name;
    }

    public int getStateValue() {
        return stateValue;
    }

    public boolean isIsSet() {
        return isSet;
    }
    
}
