/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.enumactuator.example;

import java.io.File;
import java.io.IOException;
import teit.enumactuator.model.EnumActuator;
import teit.enumactuator.model.EnumControl;

/**
 *
 * @author trang
 */
public class SwitchActuatorGenerator {

    public static void main(String[] args) throws IOException {
        EnumActuator switch1 = generate("switch1");
        System.out.println(switch1.toJSON());
        switch1.toJSONFile(new File("switch.json"));
    }

    public static EnumActuator generate(String name) {
        EnumActuator switch1 = new EnumActuator(name)
                .hasControl(0, new EnumControl("switch-on").hasCondition("state", "OFF").hasEffect("state", "ON"))
                .hasControl(1, new EnumControl("switch-off").hasCondition("state", "ON").hasEffect("state", "OFF"));

        String json = switch1.toJSON();
        return switch1;

    }

}
