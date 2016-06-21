package teit.sensor.CSVFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

import teit.sensor.APIs.InputAdaptor;

/**
 *
 * @author Trang
 */
public class CSVDataAdaptor implements InputAdaptor {

    final static Logger LOGGER = org.apache.log4j.Logger.getLogger(CSVDataAdaptor.class);

    private BufferedReader br = null;
    private String[] keyset;
    private String firstLine = null;
    private final String cvsSplitBy = ",";
    String csvFileName;
    private String sensorID=null;

    @Override
    public boolean init(Properties prop) {
        csvFileName = (String) prop.get("data.csv.fileName");
        String addSensorID=(String) prop.get("data.csv.addSensorID");
        if (addSensorID.trim().equals("true")){
            sensorID = csvFileName.substring(0,csvFileName.lastIndexOf("."));
        }
        LOGGER.debug("CSV File:" + csvFileName);
        try {
            br = new BufferedReader(new FileReader(csvFileName));
            firstLine = br.readLine();
            keyset = firstLine.split(cvsSplitBy);
            LOGGER.debug("Keyset:" + Arrays.toString(keyset));
            return true;
        } catch (FileNotFoundException ex) {
            LOGGER.debug("Cannot find file: " + prop.get("data.csv.fileName"), ex);
            return false;
        } catch (IOException ex1) {
            LOGGER.debug("Cannot open file: " + prop.get("data.csv.fileName"), ex1);
            return false;
        }
    }

    @Override
    public Map<String, String> getNextdata() {
        Map<String, String> dataMap = new HashMap<>();
        try {
            String line = br.readLine();            
            // if reach the end of the file, recreate br and read from the top of file
            if (line == null) {
                br = new BufferedReader(new FileReader(csvFileName));
                firstLine = br.readLine();
                line = br.readLine();
            }
            LOGGER.debug("CSV read one line: " + line);
            String[] lineArray = line.split(cvsSplitBy);
            for (int i = 0; i < lineArray.length; i++) {
                dataMap.put(keyset[i].trim(), lineArray[i].trim());
            }
            if (sensorID!=null && !sensorID.isEmpty()){
                dataMap.put("sensorid", sensorID);
            }
            return dataMap;

        } catch (IOException ex) {
            LOGGER.debug("Error to read next line in CSV file", ex);
            return null;
        }

    }

    @Override
    public void close() {
        try {
            if (br != null) {
                br.close();
            } else {
                LOGGER.debug("Something wrong, the CSV file was not opened, but you call close !");
            }
        } catch (IOException ex) {
            LOGGER.debug(ex);
        }
    }

}
