/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedenvironment.Functions;

import teit.emulatedenvironment.Model.GenFunction;

/**
 *
 * @author Trang
 */
public class Bouncing implements GenFunction{
    public Double min;
    public Double max;
    public Double init;
    public Double step;
    public Double current;

    public Bouncing(Double min, Double max, Double init, Double step) {
        this.min = min;
        this.max = max;
        this.init = init;
        this.step = step;
    }


    @Override
    public Double getValue() {
        if(init <= min ) {
                return init = min;
                }
        else if(init < max) {
            return init = init + step;
        }
        else if(init >=max){
            return init = max;
        }
        return current = init;
    }
    
}
