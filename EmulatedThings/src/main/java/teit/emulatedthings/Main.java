/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedthings;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import teit.enumactuator.model.EnumActuator;
import teit.rangeactuator.model.RangeActuator;

/**
 * TODO: The main class has full of hard-code. Need to be rewritten.
 * @author Trang
 */
public class Main {

    static final String DEFAULT_DATA_FOLDER = "data";

    public static void main(String[] args) throws IOException {
        // check if data folder is created
        File dataFolder = new File(DEFAULT_DATA_FOLDER);

        // String enumCurrentState;
        Map<String, EnumActuator> enumMap = new HashMap<>();
        Map<String, RangeActuator> rangeMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        RangeActuator rangeLevel;
        ObjectMapper mapperLevel = new ObjectMapper();
        rangeLevel = mapperLevel.readValue(new File("levelDescription.data.json"), RangeActuator.class);

        RangeActuator rangeColor;
        ObjectMapper mapperColor = new ObjectMapper();
        rangeColor = mapperColor.readValue(new File("colorDescription.data.json"), RangeActuator.class);

        EnumActuator onOff;
        ObjectMapper mapperOnOff = new ObjectMapper();
        onOff = mapperOnOff.readValue(new File("OnOff.data.json"), EnumActuator.class);

        enumMap.put(onOff.getName(), onOff);
        rangeMap.put(rangeLevel.getName(), rangeLevel);
        rangeMap.put(rangeColor.getName(), rangeColor);

        String[] fullName = args[0].split("\\.");
        //System.out.println("ARG 0 : " + args[0]);
        //System.out.println("THE LENGTH IS: " + fullName.length);
        if (fullName.length < 2) {
            System.out.println("Wrong Command");
            return;
        }
        String actuatorName = fullName[0];
        String actionName = fullName[1];

        String temp;
        int count = 0;
        for (String key : enumMap.keySet()) {
            temp = enumMap.get(key).getName();
            //enumCurrentState = enumMap.get(key).getCurrentState();
            if (temp.equals(actuatorName)) {
                count++;
                switch (actionName.trim()) {
                    case "current-state":
                        System.out.println(enumMap.get(key).getContext());
                        break;
                    default:
                        EnumActuator.INVOKE_RESULT stateX = enumMap.get(key).invoke(actionName, null);
                        System.out.println(stateX);                        
                        mapperOnOff.writerWithDefaultPrettyPrinter().writeValue(new File("OnOff.data.json"), enumMap.get(key));
                        break;

                }

            }
        }

        for (String k : rangeMap.keySet()) {

            temp = rangeMap.get(k).getName();
            if (temp.equals(actuatorName)) {
                count++;
                switch (actionName.trim()) {
                    case "current-state":
                        System.out.println(rangeMap.get(k).getCurrentState());
                        break;
                    default:
                        int stateY = rangeMap.get(k).invoke(actionName, rangeMap.get(k), args);
                        if (stateY == 0) {
                            System.out.println("Unknown command of actuator" + k);
                        }
                }
                rangeMap.get(k).invoke(actionName, rangeMap.get(k), null);

            }
        }
        if (count == 0) {
            System.out.println("Not found actuator:" + actuatorName);
        }

    }
}
