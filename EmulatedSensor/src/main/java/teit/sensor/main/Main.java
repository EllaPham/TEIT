package teit.sensor.main;

import teit.sensor.APIs.InputAdaptor;
import teit.sensor.APIs.OutputAdaptor;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.FileWatchdog;

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
    
    static private class SomeWatchFile extends FileWatchdog {

    protected SomeWatchFile(String filename) {
        super(filename);
    }

    @Override
    protected void doOnChange() {
        Main.loadSettings();
    }

} 

    final static Logger LOGGER = Logger.getLogger(Main.class);
    
    // Đưa các biến rate và Setting của Output ra thành biến static của hàm Main
    static public String rate;   
    static public InputAdaptor inputAdaptor;
    static public OutputAdaptor outputAdaptor;   
    static public String sensorid;
   
    
    public static void main(String[] args) throws Exception {
        
        
        LOGGER.debug("Starting sensor...");
        loadSettings();
        SomeWatchFile someWatchFile = new SomeWatchFile ("sensor.conf");
        someWatchFile.start();
        try {
            LOGGER.debug("Starting to read data .... \n");
            while (true) {
                Map<String, String> dataItem = inputAdaptor.getNextdata();
                LOGGER.debug("Data item is read:" + dataItem);

                if (!dataItem.containsKey("sensorid")) {
                    dataItem.put("sensorid", sensorid);
                }
                
                if (dataItem == null) {
                    LOGGER.debug("Input data reading complete, stop now!");
                    break;
                }

                boolean pushResult = outputAdaptor.pushData(dataItem);

                if (pushResult == false) {
                    LOGGER.debug("Cannot push data to the output, quit now!");
                    break;
                }

                LOGGER.debug("Sleeping for " + rate + " mili-seconds ...\n");
                Thread.sleep(Long.parseLong(rate.toString()));  // đay nay, rate nay ko phai Main.rate
            }
        } finally {
            inputAdaptor.close();
        }

    }
    public static  void loadSettings(){
          String inputClassName;
          String  outputClassName;
         
          
         Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("sensor.conf");
            prop.load(input);
            
        rate = prop.getProperty("rate").trim();        
        inputClassName = prop.getProperty("data");
        outputClassName = prop.getProperty("platform");
        sensorid = prop.getProperty("sensorID").trim(); 
            
        LOGGER.debug("Input : " + inputClassName);
        LOGGER.debug("Output: " + outputClassName);
        
        Class<?> dataAdaptorClass = Class.forName(inputClassName.trim());  
        Constructor<?> consData = dataAdaptorClass.getConstructor();
        inputAdaptor = (InputAdaptor) consData.newInstance();

        // load output class
        Class<?> adaptorClass = Class.forName(outputClassName.trim());    
        Constructor<?> cons = adaptorClass.getConstructor();
        outputAdaptor = (OutputAdaptor) cons.newInstance();

         boolean initInput = inputAdaptor.init(prop);
         boolean initOutput = outputAdaptor.init(prop);

        if (initInput == false || initOutput == false) {
            LOGGER.debug("Cannot init the input or output... Quit !");
            return;
        }
            
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    }
  

