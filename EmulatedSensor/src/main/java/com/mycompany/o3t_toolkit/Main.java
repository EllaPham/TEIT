package com.mycompany.o3t_toolkit;

import teit.sensor.APIs.InputAdaptor;
import teit.sensor.APIs.OutputAdaptor;
import java.util.Map;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;
import org.apache.log4j.Logger;
import teit.sensor.MQTT.MQTTOutput;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Trang
 */
public class Main {
    final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        LOGGER.debug("Starting sensor...");
        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("sensor.conf");
        prop.load(input);

        String inputClassName = prop.getProperty("data").trim();
        String outputClassName = prop.getProperty("platform").trim();

        LOGGER.debug("Input : " + inputClassName);
        LOGGER.debug("Output: " + outputClassName);

        // load input class
        Class<?> dataAdaptorClass = Class.forName(inputClassName.trim());
        Constructor<?> consData = dataAdaptorClass.getConstructor();
        InputAdaptor inputAdaptor = (InputAdaptor) consData.newInstance();

        // load output class
        Class<?> adaptorClass = Class.forName(outputClassName.trim());
        Constructor<?> cons = adaptorClass.getConstructor();
        OutputAdaptor outputAdaptor = (OutputAdaptor) cons.newInstance();

        boolean initInput = inputAdaptor.init(prop);
        boolean initOutput = outputAdaptor.init(prop);
         
        if (initInput == false || initOutput == false) {
            LOGGER.debug("Cannot init the input or output... Quit !");
            return;
        }
        try {
            LOGGER.debug("Starting to read data ....");
            while (true) {
                Map<String, String> dataItem = inputAdaptor.getNextdata();
                LOGGER.debug("Data item is read:" + dataItem);
                if (dataItem == null) {
                    LOGGER.debug("Input data reading complete, stop now!");
                    break;
                }
                
                boolean pushResult = outputAdaptor.pushData(dataItem);
                
                if (pushResult == false) {
                    LOGGER.debug("Cannot push data to the output, quit now!");
                    break;
                }

                LOGGER.debug("Sleeping for " + prop.get("rate") + " mili-seconds ...\n");
                Thread.sleep(Long.parseLong(prop.get("rate").toString()));
                
            }
        } finally {
            inputAdaptor.close();
        }
         
         
    }

}
