/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.o3t_toolkit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * This class contain several functions to call REST services in generic way
 *
 * @author trang
 */
public class GenericRESTFunction {

    final static Logger LOGGER = Logger.getLogger(GenericRESTFunction.class);

    public static String sendPOST(String url, String postData, Map<String, String> requestProperties) throws MalformedURLException, IOException {
        // TODO: move the code of sendPost to here and use above parameters

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        for (String key : requestProperties.keySet()) {
            con.setRequestProperty(key, requestProperties.get(key));
        }
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postData);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        LOGGER.debug("Sending 'POST' request to URL : " + url + ". Request properties: " + requestProperties + "Post parameters : " + postData + "Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        LOGGER.debug(response.toString());

        // return the output of the REST: combine the "inputLine" into one String.
        return null;
    }

    public static String sendGET(String url) throws MalformedURLException, IOException {

        // TODO: to the same thing with sendPOST, and return the data.
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET      
        con.setRequestMethod("GET");
        con.setDoInput(true);
        con.connect();

        int responseCode = con.getResponseCode();
        LOGGER.debug("Sending 'GET' request to URL : " + url + "Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result		
        String responseData = response.toString();
        LOGGER.debug(responseData);
        return responseData;
    }

}
