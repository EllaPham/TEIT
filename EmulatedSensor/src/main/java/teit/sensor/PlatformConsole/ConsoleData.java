/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.PlatformConsole;

import teit.sensor.APIs.InputAdaptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Trang
 */
public class ConsoleData implements InputAdaptor{

    @Override
    public Map<String, String> getNextdata() {
        //To change body of generated methods, choose Tools | Templates.
        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter a string:");
        String userString = keyboard.nextLine();
        System.out.println("USER INPUT KEYBOARD:" +userString);
        Map<String, String> consoleDataMap = new HashMap<>();
        consoleDataMap.put("CONSOLE", userString);
        System.out.println("console Data Map:" + consoleDataMap);
        return consoleDataMap;       
    }

    @Override
    public boolean init(Properties prop) {
        return true;
    }

    @Override
    public void close() {
       // nothing to do
    }
}
    

