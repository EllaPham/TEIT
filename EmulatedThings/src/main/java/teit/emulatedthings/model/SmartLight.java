/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedthings.model;

import teit.enumactuator.example.SwitchActuatorGenerator;
import teit.emulatedthings.EmulatedThing;

/**
 *
 * @author Trang
 */
public class SmartLight {
   
    public static EmulatedThing generate(String name){
        EmulatedThing light = new EmulatedThing(name, EmulatedThing.THING_PUSH_PROTOCOL.LWM2M);
        light.getEnumActuators().put(0, SwitchActuatorGenerator.generate("onoff-switch"));
        light.getEnumActuators().put(1, SwitchActuatorGenerator.generate("color-switch"));
        return light;
    }
    
}
