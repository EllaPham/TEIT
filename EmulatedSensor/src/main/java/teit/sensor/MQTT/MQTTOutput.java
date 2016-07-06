/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.MQTT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
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
public class MQTTOutput implements OutputAdaptor {

    final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MQTTOutput.class);
    public MqttClient queueClient = null;
    private String topic = "unknown";
    static ObjectMapper mapper;

    public MQTTOutput() {
        mapper = new ObjectMapper();
    }

    @Override
    public boolean init(Properties prop) {
        String broker = prop.getProperty("platform.mqtt.url");
        topic = prop.getProperty("platform.mqtt.topic");
        LOGGER.debug("MQTTAdaptor -- broker: " + broker + " -- topic: " + topic);

        try {
            queueClient = new MqttClient(broker, UUID.randomUUID().toString());
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
        int qos = 2;
        try {
            String content = mapper.writeValueAsString(values);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);

            queueClient.publish(topic, message);
            LOGGER.debug("Published: " + values);

        } catch (JsonProcessingException | MqttException me) {
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
        return true;
    }

}
