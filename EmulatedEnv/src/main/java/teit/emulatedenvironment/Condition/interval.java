/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedenvironment.Condition;

import java.util.Date;
import teit.emulatedenvironment.Model.GenCondition;

/**
 *
 * @author Trang
 */
public class interval implements GenCondition{
public Long delay;
public Long lastValue = 0L;
    @Override
    public boolean check() {
        Long current = new Date().getTime();
        if(current - delay > lastValue) {
            lastValue = current;
            return true;
        }
       return false;
    }

    public interval(Long delay) {
        this.delay = delay;
    }
    
    
}
