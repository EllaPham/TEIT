/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.APIs;

import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Trang
 */
public interface InputAdaptor {

    public Map<String, String> getNextdata();

    public boolean init(Properties prop);

    public void close();

}
