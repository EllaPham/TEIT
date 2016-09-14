/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.enumactuator.example;

import java.io.File;
import java.io.IOException;
import teit.enumactuator.model.EnumControl;
import teit.enumactuator.model.EnumActuator;

/**
 *
 * @author Trang
 */
public class DoorActuatorGenerator {

    public static void main(String[] args) throws IOException {
        EnumActuator door = generate("myDoor");
        System.out.println(door.toJSON());
        door.toJSONFile(new File("smart-door.json"));
    }

    public static EnumActuator generate(String name) {

        EnumActuator door = new EnumActuator(name)
                .hasControl(0, new EnumControl("open")
                        .hasCondition("locked", "NO")
                        .hasCondition("state", "CLOSED")
                        .hasEffect("state", "OPENED"))
                .hasControl(1, new EnumControl("close")
                        .hasCondition("state", "OPENED")
                        .hasEffect("state", "CLOSED"))
                .hasControl(2, new EnumControl("lock")
                        .hasCondition("locked", "NO")
                        .hasCondition("state", "CLOSED")
                        .hasEffect("locked", "YES"))
                .hasControl(3, new EnumControl("unlock")
                        .hasCondition("locked", "YES")
                        .hasEffect("locked", "NO"));
        return door;

    }

}
