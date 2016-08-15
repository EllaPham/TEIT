/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedenvironment.Condition;

import teit.emulatedenvironment.Model.GenCondition;

/**
 *
 * @author Trang
 */
public class TimeRange implements GenCondition{
    Double start;
    Double stop;
    

    @Override
    public boolean check() {
       return true;
    }

    public TimeRange(Double start, Double stop) {
        this.start = start;
        this.stop = stop;
    }
    
}
