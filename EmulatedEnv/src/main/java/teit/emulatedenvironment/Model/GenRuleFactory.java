/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedenvironment.Model;

import teit.emulatedenvironment.Condition.interval;
import teit.emulatedenvironment.Functions.Bouncing;
import teit.emulatedenvironment.Functions.RandomData;

/**
 *
 * @author Trang
 */
public class GenRuleFactory  {   
 public static GenCondition getCondition(String[] input){
     GenCondition condition = null;
     switch(input[0]){
         case "interval":
             System.out.println("INTERVAL PARA:" + input[1]);
             condition = new interval(Long.parseLong(input[1]));
             break;
             
     }    
    return condition;
}
 
public static GenFunction getFunction(String[] input){
    GenFunction function = null;
    switch(input[0]){
         case "random":
             System.out.println("RANDOM PARA:" + input[1] + ":" + input[2]);
             function = new RandomData(Double.parseDouble(input[1]),Double.parseDouble(input[2]));
             break;
         case "bouncing":
            function = new Bouncing(Double.parseDouble(input[1]),Double.parseDouble(input[2]),Double.parseDouble(input[3]), Double.parseDouble(input[4]));
            break;
     }    
    
    return function;
}     
            
}
