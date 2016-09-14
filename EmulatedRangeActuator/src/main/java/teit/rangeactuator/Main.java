/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.rangeactuator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import teit.rangeactuator.model.RangeActuator;

/**
 *
 * @author Trang
 */
public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {

            RangeActuator range;
            ObjectMapper mapper = new ObjectMapper();
            try {
                range = mapper.readValue(new File("RangeActuator.data"), RangeActuator.class);

                switch (args[0]) {
                    case "current-state":
                        System.out.println(range.getCurrentState());
                        return;
                    default:
                        range.invoke(args[0],range, null);
                        if (range.getControlByName(args[0]) == null) {
                            System.out.println("Unknown command !");
                            return;
                        }
                }
                if (range.getCurrentState() > range.getEndRange()) {
                    range.setCurrentState(range.getEndRange());
                }
                if (range.getCurrentState() < range.getStartRange()) {
                    range.setCurrentState(range.getStartRange());
                }

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("RangeActuator.data"), range);

            } catch (IOException ex) {
                System.out.println("Cannot load RangeActuator.data. Please check !");
                ex.printStackTrace();
            }

        } else {
        }
    }
}
