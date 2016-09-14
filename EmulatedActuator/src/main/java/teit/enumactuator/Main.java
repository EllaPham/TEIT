/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.enumactuator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import teit.enumactuator.model.EnumControl;
import teit.enumactuator.model.EnumActuator;

/**
 *
 * @author trang
 */
public class Main {

    private static final File DATA_FILE = new File("actuator.json");

    public static void main(String[] args) throws IOException {

        EnumActuator actuator = EnumActuator.fromJsonFile(DATA_FILE);
        if (actuator == null) {
            System.out.println("Data file not found: " + DATA_FILE);
            return;
        }

        if (args.length > 0) {
            String command = args[0];

            switch (command) {
                case "context":
                    System.out.println(mapToJson(actuator.getContext()));
                    break;
                case "action-list":
                    for (EnumControl control : actuator.getControls().values()) {
                        System.out.println(control.getName());
                    }
                    break;
                default:
                    if (actuator.findControlByName(command) == null) {
                        System.out.println("Unknown command !");
                        return;
                    }
                    // TODO: do not support parameter at the moment
                    EnumActuator.INVOKE_RESULT result = actuator.invoke(command, new String[1]);
                    System.out.println(result);
                    if (result == EnumActuator.INVOKE_RESULT.SUCCESSFUL) {
                        actuator.toJSONFile(DATA_FILE);
                        System.out.println(mapToJson(actuator.getContext()));
                    }

                    break;
            }

            return;
        } else {
            System.out.println(" -- CONTEXT --");
            System.out.println(mapToJson(actuator.getContext()));

            System.out.println(" -- ACTIONS --");
            for (EnumControl control : actuator.getControls().values()) {
                System.out.println(control.getName());
            }

        }

    }

    static private String mapToJson(Map<String, String> map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            return "";
        }
    }

}
