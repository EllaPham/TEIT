---
layout: page
title: "Emulated Sensor"
category: doc
date: 2016-06-27 12:11:17
order: 1
---

### 1. INTRODUCTION

#### 1.1. MOTIVATION

As mentioned in TEIT project motivation, development and testing phases of problems in IoT system need devices and data corresponding to real scenarios. Sensor is a basic part of  IoT system. The problem is needing to create a variety of sensors dynamically. Emulated Sensor solved the problem that needs to use a lot of sensor types in order to provide for testing IoT applications. Using Emulated Sensor for development and experiments is easier, more flexible and cheaper than setting up real sensors. If using, that means sensors is set up already, then it is still easy.

#### 1.2. OVERVIEW

Emulated Sensor emulates operation principles of a real actuator, including 3 steps:

* **Step 1**, sensor reads data from environment, such as temperature, humidity, device status. These collected data are often in different formats, example: data-name, value, data-types (the temperature could be C or F, field name is temperature or  temp)

* **Step 2**, sensor helps to transform specific data of the environment to a standard format

* **Step 3**, sensor sends data to platforms or other outputs. In this step, needing a transformation from above standard format to a suitable format corresponding to each output.

Emulated Sensor is highly configurable to adapt with multiple kinds of input and output. In development environment, developers can quickly create sensors, deploy and run many instances, and produce heterogeneous resources and data.

Emulated Sensor also acts as a virtual sensor in real system by aggregating multiple sensing data and transforming into a suitable format. By this sense, multiple real sensors can be preprocessed by the virtual sensors before forwarding to the destination, e.g. to filter and collate data items, or to execute lightweight analytics.

### 2. ARCHITECTURE

![Sensor Architecture](../images/SensorProcess.png "The process of sensor")

* **Data:** collecting from real sensors with a variety of formats

* **Input Adaptor:** transforming data of real sensors from different formats to key/value format

* **Output Adaptor:** transforming key/value format to the suitable format corresponding to specific provider.
* **Provider:** A platform or endpoint that can receive data.
* **Properties file:** Selecting input data-type and provider are configured in a configuration file named *sensor.conf*. The structure of  *sensor.conf* file includes:

  * Configuration for Emulated Sensor
	
  * Configuration for Input
	
  * Configuration for Output
	
### 3. INPUT ADAPTOR 

#### 3.1. CSV ADAPTOR 

The basic input of Emulated Sensor is CSV files. The purpose of using csv file is emulating an existing scenario. For example, user needs to emulate the temperature of a room in one day, these data are  recorded  in CSV file (log file) 
The structure of CSV file as below:

* The first line consists of field names (the first line is header) 

* The next lines store data records, each record is a line

* Data columns are splitted by comma

* By default, Emulated sensor reads *sensor.data* file in the current folder for the data 

* CSV Adaptor reads the first line to extract name of fields  After each cycle, sensor reads the next line in CSV file. Data columns in the record are assigned corresponding to fields in header. The reading rewinds to the beginning when reaching EOF.

		Ex: CSV file with name *sensor.data*
		timestamp,cpu,ram,computername
		20052016,1.3,500,TRANGPC
		20052016,1.5,600,TRANGPC
		20052016,1.6,800,TRANGPC
		20052016,1.4,900,TRANGPC
		20052016,1.3,100,TRANGPC
		20052016,1.2,200,TRANGPC
		20052016,1.6,300,TRANGPC
		20052016,1.9,400,TRANGPC

For example, after each cycle, output data in Map format as below: 

    {timestamp=20052016, ram=600, cpu=1.5, computername=TRANGPC, sensorid=123}
    
#### 3.2. CONSOLE ADAPTOR 

User can type data directly from keyboard.

#### 3.3. SYSTEM MONITORING ADAPTOR 

User data getting from sensor, in particular, these data belong to laptop, measuring RAM, CPU. Through this example, we can extend receiving real data using Emulated Sensor. To Assume that we have N sensors, sending data to N data points. On gateway, 1 sensor receives all the data in one format. For example:  there are 3 sensors in a room: 1 temperature sensor, 1 fire alarm sensor and 1 humidity sensor. All these sensors are transmitted through Emulated Sensor into 4 data points, indicating current condition of the room

### 4. OUTPUT ADAPTOR 

#### 4.1. CONSOLE PLATFORM 

Real data of sensor are  received directly from keyboard, then they are printed on console screen

#### 4.2. SPARKFUN PLATFORM 

	url: https://data.sparkfun.com/ 
	
Data reading from sensor are sent to IoT platform sparkfun through Sparkfun APIs

#### 4.3. THINGSPEAK

	url: https://thingspeak.com/ 
	
Data reading from sensor are sent to IoT platform thingspeak through Thingspeak APIs

### 5. USAGE

#### 5.1. EMULATED SENSOR CONFIGURATION 

TEIT configures for Emulated Sensor through only configuration file *sensor.conf*

* **Sensor configuration:**

  * **rate:** User sends data from Emulated Sensor to outputs regularly. Duration between 2 submissions is setting up in *sensor.conf* by using a parameter *rate* (e.g. rate = 5000)

  * **sensorID:** Each sensor can have an ID. This field can be adapt with different standard depending on the system. The sensor ID will be injected into each the data items for identifying the data.

* **Input Configuration:** Data format configuration of Emulated Sensor

  * **CSV file:** 
 
			data: data=teit.sensor.CSVFile.CSVDataAdaptor 
		
  * **Input data from keyboard:**

			data=teit.sensor.PlatformConsole.ConsoleData
    
  * **Customer data:**
 
	* **Sparkfun platform:**
  
			data=teit.sensor.PlatformSparkFun.LaptopData
		
	* **Thingspeak platform:**
  
			data=teit.sensor.PlatformThingSpeak.ThingSpeakData 
    
* **Output Configuration:** Configuration for the place that data are sent to 

   * **Console Platform:**

			platform=teit.sensor.PlatformConsole.ConsolePlatform
		
   * **Thingspeak Platform:**

			platform=teit.sensor.PlatformThingSpeak.ThingSpeakPlatform
		
   * **Sparkfun Platform:**

			platform=teit.sensor.PlatformSparkFun.SparkfunPlatform
    
   * **MQTT:** 

			platform=teit.sensor.MQTT.MQTTOutput 
		
#### 5.2. RUN EMULATED SENSOR 

After data preparation and configuration in *sensor.conf*

In command line, typing this command as below:

		java -jar target\EmulatedSensor-1.0-SNAPSHOT.jar
		
#### 5.3. USING SCRIPT TO RUN SENSOR

To execute operations of sensor, we can use the sensor script. The script is run in the same folder of the sensor artifacts. Some examples as below: 

```sh
$ ./sensor.sh list-commands
start
stop
change-rate <rate>
connect-console
connect-mqtt <broker> [topic]
```
    
### 6. SCOPE AND DEVELOPMENT ORIENTATION 

Now, we have implemented the basic process of Emulated Sensor

Sensor uses 2 adaptor types: Input Adaptor and Output Adaptor

* **Input Adaptor:** Input Adaptor has responsibility in transforming data from a variety of formats (e.g. csv, json, xml,...) to only format in Map<String,String>. This map is input data for Emulated Sensor. Now, we have accomplished: 

 * **CSVAdaptor:** the adaptor for csv format. In future, TEIT will implement with a lot of Adaptors that inherit InputAdaptor in many data formats (e.g. json, xml,...)
 
 * **SystemMonitoringAdaptor:** sensing data of system (e.g. CPU, RAM,...)

* **Output Adaptor:** Output Adaptor has responsibility in transforming data from only format Map<String,String> to a variety of outputs, each output is corresponding to each provider. Now, TEIT has Adaptors for providers: Sparkfun, thingspeak, MQTT and console. In future, TEIT will implement output adaptors corresponding to providers, these adaptors inherit Output Adaptor.

 * **Properties file:** Properties file is unique configuration file  for sensor that is provided to user. User uses this file to configure to sensor depending on their require. Now, Configuration file of TEIT includes: 

    * Allowing to choose input data and output (provider) 
    * Configure delay time for 2 times sequent data sending
    * Configure parameters for adaptors: CSV, sparkfun, thingspeak and MQTT
    * Configure some properties for sensor. Now, setting value for sensorID, sensorName

In future, TEIT will define other properties for a variety of sensors and other providers.

