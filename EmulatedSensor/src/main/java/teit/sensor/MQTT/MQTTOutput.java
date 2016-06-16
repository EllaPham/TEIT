/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.MQTT;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import teit.sensor.APIs.OutputAdaptor;

/**
 *
 * @author Trang
 */
public class MQTTOutput implements OutputAdaptor{
 final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MQTTOutput.class);
    public MqttClient queueClient = null;
    public String topic = "Topic:" ;
    public MqttMessage message = null;
    @Override
    public boolean init(Properties prop) {
    String broker = prop.getProperty("platform.mqtt.url");
    String clientId = prop.getProperty("sensorID");
   
     try {
        
         queueClient = new MqttClient(broker, clientId); 
     } catch (MqttException ex) {
         Logger.getLogger(MQTTOutput.class.getName()).log(Level.SEVERE, null, ex);
     }
     try {
         queueClient.connect();
         return true;
         
     } catch (MqttException ex) {
         Logger.getLogger(MQTTOutput.class.getName()).log(Level.SEVERE, null, ex);
     }
          
            return false;
    }

    @Override
    public boolean pushData(Map<String, String> values) {
        int qos=2;
          try {
           
            System.out.println("Publishing message: "  + values);
            String content =values.toString();
          
           // for(String key:values.keySet()){content += values.get(key) ;}
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            
            queueClient.publish(topic, message);
          //  queueClient.setCallback(this);
            
//              queueClient.disconnect();
//              queueClient.close();
            System.out.println("Message published");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
      return true;
    }
    
}
