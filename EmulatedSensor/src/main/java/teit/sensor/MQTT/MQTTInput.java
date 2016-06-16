/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.MQTT;

import com.mycompany.o3t_toolkit.Main;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import teit.sensor.APIs.InputAdaptor;

/**
 *
 * @author Trang
 */
public class MQTTInput implements InputAdaptor{
 final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(Main.class);
  //private MqttClient queueClient = null;
 private final MqttMessage message = new MqttMessage();
    @Override
    public Map<String, String> getNextdata() {
         
        MQTTOutput mqttOutput = new MQTTOutput();
       // mqttOutput.queueClient.setCallback(mqttOutput);
     try {
         mqttOutput.queueClient.subscribe(mqttOutput.topic);
       LOGGER.debug("Public topic:" +mqttOutput.topic );
        LOGGER.debug("Subscribe message:" +message.getPayload());
     } catch (MqttException ex) {
         Logger.getLogger(MQTTInput.class.getName()).log(Level.SEVERE, null, ex);
     }
     
      return null;
    }

    @Override
    public boolean init(Properties prop) {
       

        return true;
    }

    @Override
    public void close() {
        
    }
    
}
