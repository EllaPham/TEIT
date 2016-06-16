/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.PlatformConsole;

import teit.sensor.APIs.OutputAdaptor;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Trang
 */
public class ConsolePlatform implements OutputAdaptor {

    @Override
    public boolean pushData(Map<String, String> values) {
        //To change body of generated methods, choose Tools | Templates.
        System.out.println("value");
        return true;
    }

    @Override
    public boolean init(Properties prop) {
        return true;
    }
}
