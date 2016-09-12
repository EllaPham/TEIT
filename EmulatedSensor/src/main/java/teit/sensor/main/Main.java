package teit.sensor.main;

import java.io.File;
import teit.sensor.APIs.InputAdaptor;
import teit.sensor.APIs.OutputAdaptor;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
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
    static public String sensorType;
    static final String CONFIG_FILE = "sensor.conf";

    public static void main(String[] args) throws Exception {

        LOGGER.debug("Starting sensor...");
        loadSettings();

        TimerTask task = new FileWatcherWithTimer(new File(CONFIG_FILE)) {
            @Override
            protected void onChange(File file) {
                Main.loadSettings();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, new Date(), 1000); // check file every second

        try {
            LOGGER.debug("Starting to read data .... \n");
            while (true) {
                Map<String, String> dataItem = inputAdaptor.getNextdata();
                LOGGER.debug("Data item is read:" + dataItem);

                if (dataItem == null) {
                    LOGGER.debug("Input data reading complete, stop now!");
                    break;
                }

                if (!dataItem.containsKey("sensorid")) {
                    dataItem.put("sensorid", sensorid);
                }

                if (!dataItem.containsKey("sensortype")) {
                    dataItem.put("sensortype", sensorType);
                }

                boolean pushResult = outputAdaptor.pushData(dataItem);

                if (pushResult == false) {
                    LOGGER.debug("Cannot push data to the output, quit now!");
                    break;
                }

                LOGGER.debug("Sleeping for " + rate + " mili-seconds ...\n");
                Thread.sleep(Long.parseLong(rate));
            }
        } finally {
            inputAdaptor.close();
        }

    }

    static Properties oldProp = null;

    public static void loadSettings() {
        LOGGER.debug("(Re)loadding settings...");
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(CONFIG_FILE);
            prop.load(input);

            rate = prop.getProperty("rate").trim();
            LOGGER.debug("The rate is update to: " + rate);

            String inputClassName = prop.getProperty("data").trim();
            String outputClassName = prop.getProperty("platform").trim();
            sensorid = prop.getProperty("sensorID").trim();
            sensorType = prop.getProperty("sensorType").trim();

            boolean initInput = true;
            boolean initOutput = true;

            LOGGER.debug("Checking.. Old input: " + inputAdaptor + ". New input class:" + inputClassName);
            if (inputAdaptor == null || fieldChanged("data", prop, oldProp)) {
                LOGGER.debug("InputAdaptor is changed. Initiate new input adaptor : " + inputClassName);
                prop.load(input);
                Class<?> dataAdaptorClass = Class.forName(inputClassName.trim());
                Constructor<?> consData = dataAdaptorClass.getConstructor();
                inputAdaptor = (InputAdaptor) consData.newInstance();
                initInput = inputAdaptor.init(prop);
            }

            LOGGER.debug("Checking.. Old output: " + outputAdaptor + ". New output class:" + outputClassName);
            if (outputAdaptor == null || fieldChanged("platform", prop, oldProp)) {
                LOGGER.debug("OutputAdaptor is changed. Initiate new output adaptor : " + inputClassName);
                prop.load(input);
                Class<?> adaptorClass = Class.forName(outputClassName.trim());
                Constructor<?> cons = adaptorClass.getConstructor();
                outputAdaptor = (OutputAdaptor) cons.newInstance();
                initOutput = outputAdaptor.init(prop);
            }
            if (initInput == false || initOutput == false) {
                LOGGER.debug("Cannot init the input or output... Quit !");
                System.exit(1);
            }
            oldProp = prop;  // save current prop to check in next step
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean fieldChanged(String fieldPrefix, Properties prop, Properties oldProp) {
        Set<Entry<Object, Object>> entrySet = prop.entrySet();
        LOGGER.debug("Ckeck if a field is changed. Prefix:" + fieldPrefix);
        for (Entry<Object, Object> entry : entrySet) {
            String key = entry.getKey().toString();
            LOGGER.debug("Entry in prop: key: " + entry.getKey() + " -- new value: " + entry.getValue() + " -- old value: " + oldProp.get(key));
            if (!oldProp.get(key).toString().trim().equals(prop.get(key).toString().trim()) && key.startsWith(fieldPrefix)) {
                LOGGER.debug("   --> The field is changed");
                return true;
            }
        }
        return false;
    }
}
