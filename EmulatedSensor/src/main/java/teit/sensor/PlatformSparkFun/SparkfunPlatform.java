/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.PlatformSparkFun;

import teit.sensor.APIs.OutputAdaptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.o3t_toolkit.GenericRESTFunction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contain the function related to data.sparkfun.com
 *
 * @author trang
 */
public class SparkfunPlatform implements OutputAdaptor {

    String pubkey;
    String prikey;

    @Override
    public boolean init(Properties prop) {
        pubkey = prop.getProperty("platform.sparkfun.publickey").trim();
        prikey = prop.getProperty("platform.sparkfun.privatekey").trim();
        return true;
    }

    @Override
    public boolean pushData(Map<String, String> values) {
        try {
            String url = "http://data.sparkfun.com/input/" + pubkey.trim();

            String urlParameters = "";
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Phant-Private-Key", prikey.trim());

            for (String key : values.keySet()) {
                urlParameters = urlParameters + key + "=" + values.get(key) + "&";
            }
            urlParameters = urlParameters.substring(0, urlParameters.length() - 1);

            System.out.println(urlParameters);
            GenericRESTFunction.sendPOST(url, urlParameters, headerMap);

            // if the return form the REST is "1 success", return true ==> post ok. Otherwise return false ==> post failed
            return true;
        } catch (IOException ex) {
            Logger.getLogger(SparkfunPlatform.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    static public List<Map<String, String>> getFirstPageData(String publickey) throws IOException {
        // test with this : http://data.sparkfun.com/output/aGAVxjyrwZSRvlY7oAL4.json?page=1

        /**
         * TODO: 1. get the publickey, replate with above URL 2. Call GET method
         * 3. The data will return a JSON, convert to Map (this link, section
         * 1):
         * http://www.mkyong.com/java/how-to-convert-java-map-to-from-json-jackson/
         */
        //1. get the publickey, replate with above URL
        String url = "http://data.sparkfun.com/output/" + publickey + ".json?page=1";
        //System.out.println("SendGEt URL:" + url);
        //1. get the publickey, replate with above URL       
        // 3. The data will return a JSON, convert to Map 
        ObjectMapper mapper = new ObjectMapper();
        String json = GenericRESTFunction.sendGET(url);
        System.out.println("STRING JSON:" + json);

        List<Map<String, Object>> map = new ArrayList<>();
        // convert JSON string to Map
        map = mapper.readValue(json, new TypeReference<List<Map<String, String>>>() {
        });
        System.out.println("JSON TO MAP RESULT:");
        System.out.println(map);

        return null;

    }

}
