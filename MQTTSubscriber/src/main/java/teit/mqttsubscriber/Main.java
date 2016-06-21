/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.mqttsubscriber;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author hungld
 */
public class Main {

    int qos = 2;

    public static void main(String[] args) {
        // default can be tcp://iot.eclipse.org:1883
        String broker = args[0];
        final String topic = args[1];
        String clientId = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();

        // callback when message comes 
        MqttCallback callBack = new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
                System.out.println("MQTT is disconnected from topic: " + topic + ". Message: " + thrwbl.getMessage() + ". Cause: " + thrwbl.getCause().getMessage());
                thrwbl.printStackTrace();
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                String msg = new String(mm.getPayload(), StandardCharsets.UTF_8);
                System.out.println(msg);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
                System.out.println("Deliver complete to topic: " + topic);
            }
        };

        // connect and subscribe
        MqttClient queueClient;
        try {
            queueClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            queueClient.setCallback(callBack);
            queueClient.connect(connOpts);
            if (queueClient.isConnected()) {
                System.out.println("Connected to the MQTT broker: " + broker);
            } else {
                System.out.println("Failed to connect to the broker: " + broker);
            }
            queueClient.subscribe(topic);
            System.out.println("Subscribed the topic: " + topic + ".\nListening to the incoming data... \n");
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }
}
