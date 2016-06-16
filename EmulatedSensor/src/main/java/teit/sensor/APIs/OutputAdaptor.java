/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates,
 * and open the template in the editor.
 */
package teit.sensor.APIs;

import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Trang
 */
public interface OutputAdaptor {

    boolean init(Properties prop);

    public boolean pushData(Map<String, String> values);



}
