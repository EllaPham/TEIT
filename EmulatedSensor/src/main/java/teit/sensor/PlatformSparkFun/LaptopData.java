/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.PlatformSparkFun;

import teit.sensor.APIs.InputAdaptor;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.MBeanServerConnection;

/**
 *
 * @author Trang
 */
public class LaptopData implements InputAdaptor {
    @Override
    public  Map<String, String> getNextdata(){
          String ComputerName = getComputerName();
            String RAM = getRAM().toString();
            double CPU = 0;
        try {
            CPU = getCPULoad();
        } catch (Exception ex) {
            Logger.getLogger(LaptopData.class.getName()).log(Level.SEVERE, null, ex);
        }
            String cpuString = String.format("%1$,.4f", CPU);

            System.out.println("ComputerName:" + ComputerName);
            System.out.println("RAM DATA:" + RAM);
            System.out.println("CPU DATA:" + CPU);
            System.out.println("CPU Formated:" + cpuString);
                    
        Map<String,String> laptopDataMap = new HashMap<>();
            laptopDataMap.put("computername", ComputerName);
            laptopDataMap.put("cpu", cpuString);
            laptopDataMap.put("ram", RAM);
        return laptopDataMap;
    }
    static public String getComputerName() {
        Map<String, String> env = System.getenv();

        if (env.containsKey("COMPUTERNAME")) {
            return env.get("COMPUTERNAME");
        } else if (env.containsKey("HOSTNAME")) {
            return env.get("HOSTNAME");
        } else if (env.containsKey("USERNAME")) {
            return env.get("USERNAME") + "-PC";
        } else {
            return "Unknown Computer";
        }
    }
    static public StringBuilder getRAM() {

        Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();

        long freeMemory = runtime.freeMemory();
        sb.append(format.format(freeMemory / 1024));
        return sb;
    }
     static public double getCPULoad() throws Exception {

        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

        OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(
                mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);

        double cpuLoad = osMBean.getSystemCpuLoad();
        return cpuLoad;

    }

    @Override
    public boolean init(Properties prop) {
        return true;
    }

    @Override
    public void close() {
        // nothing to do
    }

   static public List<String> getFieldName(Properties prop)
   {    
       List<String> fieldNameList = new ArrayList<>();
       fieldNameList.add(prop.getProperty("sparkfun.field1").trim());
       fieldNameList.add(prop.getProperty("sparkfun.field2").trim());
       fieldNameList.add(prop.getProperty("sparkfun.field3").trim());
       return fieldNameList;
   }
}
