/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.rangeactuator.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Trang
 */
public class RangeActuator {

    public String name;
    public int startRange;
    public int endRange;
    public int currentState;
    public List<Control> controls = new ArrayList<>();

    public RangeActuator() {
    }

    public RangeActuator(String name, int startRange, int endRange, int currentState) {
        this.name = name;
        this.startRange = startRange;
        this.endRange = endRange;
        this.currentState = currentState;
    }

    public Control getControlByName(String aName) {
        for (Control c : controls) {
            if (c.getName().equalsIgnoreCase(aName)) {
                return c;
            }
        }
        return null;
    }

    public RangeActuator hasControl(Control control) {
        this.controls.add(control);
        return this;
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

    public int invoke(String actionName, RangeActuator Arange, String[] parameter) {
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
    
     public String toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public boolean toJSONFile(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, this);
            return true;
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static RangeActuator fromJsonFile(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, RangeActuator.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static RangeActuator fromJsonString(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, RangeActuator.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
