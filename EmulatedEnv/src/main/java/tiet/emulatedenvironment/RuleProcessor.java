/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiet.emulatedenvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import teit.emulatedenvironment.Model.GenFunction;
import teit.emulatedenvironment.Model.GenRule;

/**
 *
 * @author Trang
 */
public class RuleProcessor {

    List<GenRule> rules = new ArrayList<>();

    public RuleProcessor(GenRule... rules) {
        for (GenRule r : rules) {
            this.rules.add(r);
        }
    }
    
    public RuleProcessor(List<GenRule> rules) {
        for (GenRule r : rules) {
            this.rules.add(r);
        }
    }

   
    public Double getValue() {
         List<GenFunction> list =  new ArrayList<>();
        for (GenRule i : rules) {
            if (i.getCondition().check()) {
                
                list.add(i.getFunction());
            }
        }
        Random random = new Random();
        
        if (list.size() > 0) {
         
            int index = random.nextInt(list.size());
            GenFunction f = list.get(index);
            return f.getValue();
        }
        return null;
        
    }
}
