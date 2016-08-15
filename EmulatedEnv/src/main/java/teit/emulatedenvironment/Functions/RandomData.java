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
public class RandomData implements GenFunction{
Double min;
Double max;

    public RandomData(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Double getValue() {
        
        return (Math.random() * ((max - min) + 1)) + min;
    }
    
}
