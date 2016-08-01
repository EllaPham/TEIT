/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedthings.model;

import java.util.Map;
import teit.actuator.model.EnumState;
import teit.rangeactuator.model.Range;

/**
 *
 * @author Trang
 */
public class SmartLight {
    public Map<String,EnumState> enumMap;
    public Map<String,Range> rangeMap;       

    public void setEnumMap(Map<String, EnumState> enumMap) {
        this.enumMap = enumMap;
    }

    public void setRangeMap(Map<String, Range> rangeMap) {
        this.rangeMap = rangeMap;
    }

    public Map<String, EnumState> getEnumMap() {
        return enumMap;
    }

    public Map<String, Range> getRangeMap() {
        return rangeMap;
    }
    
    
}
