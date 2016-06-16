/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.PlatformThingSpeak;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.o3t_toolkit.GenericRESTFunction;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import teit.sensor.APIs.OutputAdaptor;

/**
 *
 * @author Trang
 */
public class ThingSpeakPlatform implements OutputAdaptor {

    final static Logger LOGGER = org.apache.log4j.Logger.getLogger(ThingSpeakPlatform.class);

    String writeApiKey;
    String channelID;
    // example of keymap [cpu=field1, ram=field2]
    Map<String, String> keyMap = new HashMap<>();

    @Override
    public boolean init(Properties prop) {
        this.writeApiKey = prop.getProperty("platform.thingspeak.WriteApiKey").trim();
        this.channelID = prop.getProperty("platform.thingspeak.channel").trim();
        // get the list of fields
        String url = "https://api.thingspeak.com/channels/" + channelID + "/feeds.json?results=1";
        LOGGER.debug("Collecting channel information via GET " + url);
        try {
            String json1 = GenericRESTFunction.sendGET(url);
            LOGGER.debug("Channel JSON: " + json1);
            ThingSpeakResponse TSResponse = new ObjectMapper().readValue(json1, ThingSpeakResponse.class);

            putMapCheckNull(TSResponse.getChannel().getField1(), "field1");
            putMapCheckNull(TSResponse.getChannel().getField2(), "field2");
            putMapCheckNull(TSResponse.getChannel().getField3(), "field3");
            putMapCheckNull(TSResponse.getChannel().getField4(), "field4");
            putMapCheckNull(TSResponse.getChannel().getField5(), "field5");
            putMapCheckNull(TSResponse.getChannel().getField6(), "field6");
            putMapCheckNull(TSResponse.getChannel().getField7(), "field7");
            putMapCheckNull(TSResponse.getChannel().getField8(), "field8");
            LOGGER.debug("Key map after converting is: " + keyMap);
            return true;
        } catch (IOException ex) {
            LOGGER.error("Cannot query the channel data from " + url);
            return false;
        }
    }

    private void putMapCheckNull(String key, String value) {
        if (key != null && value != null && !key.isEmpty() && !value.isEmpty()) {
            keyMap.put(key, value);
        }
    }

    @Override
    public boolean pushData(Map<String, String> values) {
        String url = "https://api.thingspeak.com/update";
        String urlParameters = "";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-THINGSPEAKAPIKEY", writeApiKey);
        LOGGER.debug("Values to push: " + values);

         for (String key : keyMap.keySet()) {            
            LOGGER.debug("Adding urlParameter. Key: %, keyMap.get(key): %");            
                urlParameters = urlParameters + keyMap.get(key).trim() + "=" + values.get(key).trim() + "&";            
        }
        // delete the last &
        urlParameters = urlParameters.substring(0, urlParameters.length() - 1);

        try {
            GenericRESTFunction.sendPOST(url, urlParameters, headerMap);
        } catch (IOException ex) {
            LOGGER.error("Error when pushing data", ex);
        }

        // if the return form the REST is "1 success", return true ==> post ok. Otherwise return false ==> post failed
        return true;

    }

}
