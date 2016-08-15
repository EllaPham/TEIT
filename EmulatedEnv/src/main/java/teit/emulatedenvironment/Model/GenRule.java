/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedenvironment.Model;

/**
 *
 * @author Trang
 */
public class GenRule {
    GenCondition condition;
    GenFunction function;

    public GenRule(GenCondition condition, GenFunction function) {
        this.condition = condition;
        this.function = function;
    }

    public GenCondition getCondition() {
        return condition;
    }

    public void setCondition(GenCondition condition) {
        this.condition = condition;
    }

    public GenFunction getFunction() {
        return function;
    }

    public void setFunction(GenFunction function) {
        this.function = function;
    }
    
}
