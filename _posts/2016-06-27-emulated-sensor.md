---
layout: page
title: "Emulated Sensor"
category: doc
date: 2016-06-27 11:11:17
order: 1
---




# 1. Introduction

Emulated Sensor emulates operating principles of a real actutor, including 3 steps:

Step 1, sensor receives data from environment, such as temprature, humidity, device parameters. This collected data is often in different formats, example: field name, datatypes (the temprature could be C or F, field name is temperature or  temp)

Step 2, sensor helps to transform specific data of the environment to a standard format

Steo 3, sensor sends data to platforms or other ouputs. In this step, needing a transformation from above standard format to a suitable format corresponding to each output.

# 2. Architecture


Data ---(InputAdaptor) ---> Emulated Sensor ----(OutputAdaptor)---> Providers
					T
					I
				  sensor.conf


**Data:** data is collected from real sensors in a variety of formats

**Input Adaptor:** transform data of real sensor from different formats to only format. standard data format has the form **Map<String, String>**

**Output Adaptor:** transforming only output data of Emulated Sensor to the suitable format corresponding to specific provider.

Selecting input datatype and provider are configured in a configuration file named “sensor.conf”. The structure of sensor.conf file includes:

Configuration for Emulated Sensor

Configuration for Input

Configuaration for Output

# 3. Input adaptor

## 3.1. CSV Adaptor

The basic input of Emulated Sensor is CSV files. The perpose of using csv file is emulation of an existing scenario. Example for, user needs to emulate the temprature of a room in a day, this data is recorded in in CSV file (log file) 
The structure of CSV file as below:

The first line is names of fields (header)

The next lines store data records, each record is a line

Data columns is seperated by comma

By default, Emulated sensor reads “sensor.data” file in tempatory folder

CSV Adaptor reads the first line to extract names of fields  After each cycle, sensor reads the next line in CSV file. Data columns in the record are assigned corresponding to fields in header


    Ex: CSV file with name “sensor.data”

    timestamp,cpu,ram,computername
    20052016,1.3,500,TRANGPC    
    20052016,1.5,600,TRANGPC   
    20052016,1.6,800,TRANGPC
    20052016,1.4,900,TRANGPC   
    20052016,1.3,100,TRANGPC   
    20052016,1.2,200,TRANGPC
    20052016,1.6,300,TRANGPC
    20052016,1.9,400,TRANGPC

Afer each  cycle, output data in Map format as below: 

    {timestamp=20052016, ram=600, cpu=1.5,     computername=TRANGPC, sensorid=123}
    
## 3.2. ConsoleData adaptor

User can input data directly from keyboard.

## 3.3. Laptop data adaptor

User data,  is data from sensor. In particular, this data belongs to laptop that measures ram, cpu and computername. Through this example, we can extend receiving real data using Emulated Sensor. Assume that we have N sensors, sending data to N data points. On gateway, 1 sensor receives all the data in one format. For example:  there are 3 temperature sensors in a room: 1 temperature, 1 fire alarm sensor and 1 humidity sensor. All these sensors is passed through Emulated Sensor into 4 data points, that indicates current condition of the room

# 4. Output adaptor: 

## 4.1.ConsolePlatform

Real data of sensor are  received directly from keyboard, then they are printed on console screen

## 4.2.Sparkfun Platform

**url:** 

	https://data.sparkfun.com/ 
	
Data reading from sensor are sent to IoT platform sparkfun through Sparkfun APIs

## 4.3. Thingspeak

**url:**

	https://thingspeak.com/
	
Data reading from sensor are sent to IoT platform thingspeak through Thingspeak APIs

# 5. Usage

## 5.1.Configure for Emulated Sensor through only configuration file “sensor.conf”

**Sensor configuration**

***rate:**

User can send data from Emulated Sensor to outputs regularly. Duration between 2 submissions is set up in “sensor.conf” through parameter “rate” (ex: rate = 5000)

***sensorID:**

**Configure cho Input** 

Data format configuration of Emulated Senssor

**CSV file:**

    data: data=teit.sensor.CSVFile.CSVDataAdaptor 
	
**Input data from keyboard:**   

    data=teit.sensor.PlatformConsole.ConsoleData
	
**Customer data:**

Sparkfun platform

    data=teit.sensor.PlatformSparkFun.LaptopData 
    
Thingspeak platform

    data=teit.sensor.PlatformThingSpeak.ThingSpeakData 

**Configure cho Output:** 

Configuration for the place that data are sent to 

Console Platform: 

    platform=teit.sensor.PlatformConsole.ConsolePlatform
    
Thingspeak Platform:

    platform=teit.sensor.PlatformThingSpeak.ThingSpeakPlatform
	
Sparkfun Platform:

    platform=teit.sensor.PlatformSparkFun.SparkfunPlatform
  
MQTT: 

    platform=teit.sensor.MQTT.MQTTOutput 
		
## 5.2. Run Emulated sensor:

After data preparation and configuration in “sensor.conf”
In command line, typing this command as below:

    java -jar target\EmulatedSensor-1.0-SNAPSHOT.jar 

