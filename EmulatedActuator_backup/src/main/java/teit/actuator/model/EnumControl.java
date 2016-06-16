/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.actuator.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hungld
 */
public class EnumControl {

    private String name;
    private List<String> parameters;
    private String startState;
    private String endState;

    public void setName(String name) {
        this.name = name;
    }

    public void setParameter(List<String> parameter) {
        this.parameters = parameter;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    public String getName() {
        return name;
    }

    public List<String> getParameter() {
        return parameters;
    }

    public String getStartState() {
        return startState;
    }

    public String getEndState() {
        return endState;
    }

    public Map<String, String> invoke(String parameters) {
        return null;
    }

}
