/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.emulatedthings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import teit.enumactuator.model.EnumActuator;
import teit.enumactuator.model.EnumControl;
import teit.rangeactuator.model.RangeActuator;

/**
 *
 * @author hungld
 */
public class EmulatedThing {

    // Thing protocol is the way the Thing update information
    // DRY protocol is default, the device does not update information to anywhere
    public enum THING_PUSH_PROTOCOL {
        LWM2M, COAP, MQTT, DRY
    }

    String name;
    THING_PUSH_PROTOCOL protocol;

    // push endpoint is used for the endpoint, e.g. LWM2M server, MQTT endpoint
    String pushEndpoint;

    // reserve for the settings, e.g. MQTT topic, username/password
    Map<String, String> pushSettings;

    // the actuator of the device, to control the device.
    // we need the integer for the resource id
    Map<Integer, EnumActuator> enumActuators = new HashMap<>();
    Map<Integer, RangeActuator> rangeActuators = new HashMap<>();

    public EmulatedThing() {
    }

    public EmulatedThing(String name, THING_PUSH_PROTOCOL protocol) {
        this.name = name;
        this.protocol = protocol;
    }

    public EmulatedThing(String name, THING_PUSH_PROTOCOL protocol, String serverEndpoint) {
        this.name = name;
        this.protocol = protocol;
        this.pushEndpoint = serverEndpoint;
    }

    public EnumActuator findEnumActuatorByName(String actuatorName) {
        for (EnumActuator actuator : enumActuators.values()) {
            if (actuator.getName().trim().equals(actuatorName.trim())) {
                return actuator;
            }
        }
        return null;
    }

    public EnumActuator findEnumActuatorByID(int id) {
        return enumActuators.get(id);
    }

    public EnumControl findControlByName(String actuatorName, String controlName) {
        EnumActuator actuator = findEnumActuatorByName(actuatorName);
        if (actuator != null) {
            for (EnumControl control : actuator.getControls().values()) {
                if (control.getName().trim().equals(controlName)) {
                    return control;
                }
            }
        }
        return null;
    }

    public EnumActuator.INVOKE_RESULT invoke(int actuatorID, String controlName) {
        EnumActuator actuator = findEnumActuatorByID(actuatorID);
        if (actuator != null) {
            return actuator.invoke(controlName, new String[1]);
        }
        return EnumActuator.INVOKE_RESULT.CONTROL_NOT_FOUND;
    }

    public EnumActuator.INVOKE_RESULT invoke(String actuatorName, String controlName) {
        EnumActuator actuator = findEnumActuatorByName(actuatorName);
        if (actuator != null) {
            return actuator.invoke(controlName, new String[1]);
        }
        return EnumActuator.INVOKE_RESULT.CONTROL_NOT_FOUND;
    }

    // GET SET
    public String getPushEndpoint() {
        return pushEndpoint;
    }

    public void setPushEndpoint(String pushEndpoint) {
        this.pushEndpoint = pushEndpoint;
    }

    public Map<String, String> getPushSettings() {
        return pushSettings;
    }

    public void setPushSettings(Map<String, String> pushSettings) {
        this.pushSettings = pushSettings;
    }

    public Map<Integer, EnumActuator> getEnumActuators() {
        return enumActuators;
    }

    public void setEnumActuators(Map<Integer, EnumActuator> enumActuators) {
        this.enumActuators = enumActuators;
    }

    public Map<Integer, RangeActuator> getRangeActuators() {
        return rangeActuators;
    }

    public void setRangeActuators(Map<Integer, RangeActuator> rangeActuators) {
        this.rangeActuators = rangeActuators;
    }

    public String getName() {
        return name;
    }

    public THING_PUSH_PROTOCOL getProtocol() {
        return protocol;
    }

}
