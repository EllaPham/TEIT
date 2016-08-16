/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiet.emulatedenvironment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import teit.emulatedenvironment.Condition.interval;
import teit.emulatedenvironment.Functions.RandomData;
import teit.emulatedenvironment.Model.GenCondition;
import teit.emulatedenvironment.Model.GenFunction;

/**
 *
 * @author Trang
 */
public class GenRuleFactory  {
    
// input is something like: conditionName:p1:p2:p3
    public static GenCondition getGetCondition(String[] input) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // split string
        
        // use switch case
        // VD: if name=interval ==> new interval (s[1],s[2]...)
        
        // tra ve 1 GenCondition. Luc nay, GenCondition la interface, dc khoi tao object tuong ung
        if (input[1].equalsIgnoreCase("interval")) {
           Class<?> conditionClass = Class.forName(input[1].trim());
           Constructor<?> cons = conditionClass.getConstructor();
           GenCondition genConditionObject = (GenCondition) cons.newInstance();
           return genConditionObject;
           
        }
        return null;
    }

    public static GenFunction getGetFunction(String[] input) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (input[2].equalsIgnoreCase("random")) {
           Class<?> funClass = Class.forName(input[2].trim());
           Constructor<?> Fcons = funClass.getConstructor();
           GenFunction genFunctionObject = (GenFunction) Fcons.newInstance();
         
           return genFunctionObject;
           
        }
        
       return null;
    }
    
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
             
     }    
    
    return function;
}

  
    
            
}
