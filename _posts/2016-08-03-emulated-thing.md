---
layout: page
title: "Emulated Thing"
category: doc
date: 2016-08-03 11:04:39
order: 3
---
### 1.INTRODUCE

#### 1.1.MOTIVATION

As we know, sensor and actuator are basic parts of an IoT system.In fact, the IoT system consists of complex devices. Each device is a collection of many sensors and actuators (e.g. light, air conditioning,...)

With different devices, the set of sensors and actuators are different from other collection about set of components (just including sensors, just including actuators or both) , quantity and  combination of elements in the creation of a real thing. Thus, the management of these devices is cumbersome and required a lot of efforts.

Giving a model of combination of sensors and actuators help to set up emulated IoT devices that become easier and more convenient, providing utilities for IoT developers in the implementation of IoT application.

#### 1.2.OVERVIEW EMULTED THINGS 

Emulated  things provide the model of combination between sensors and actuators in creating an IoT real devices. 

Implementing some common “Emulated Things” (e.g. Smartlight…) 

### 2.ARCHITECTURE

#### 2.1.PROCESS

![EmulatedThings Process](../images/EmulatedThingProcess.png "The process of emulated thing")

* **Description files:** including descriptions for the actuators of the emulated thing. In each description file, there have states and a set of control actions. Description file can be in a variety of formats (e.g. json, xml, csv,...). For the sake of compatibility with previous actuator project, we use JSON format.
All the description files will be put into a single folder. At start, the emulated thing will scan and load these file into the model.

* **Emulated thing:** including a set of generic sensors and actuators loading from the description files.

* **APIs & control:** being a collection of APIs in order to call control actions of the actuators and management of sensors’ data. These APIs are generated depending on sensors and actuators.


#### 2.2. DATA MODEL

Emulated thing consists of sensors and actuators (enum actuator & range actuator)

![EmulatedThings Data model](../images/EmulatedThingDataModel.png "The data model of emulated thing")

### 3.SMARTLIGHT

Smart light includes 3 actuators: 

* **Switch actuator** is an enum actuator that controls the switch between On state and Off state

* **Level actuator** is a range actuator that controls bright levels of smart light

* **Color actuator** is a range actuator that controls color 

As a composition of multiple actuators, the API of the SmartLight follows a naming convention to facilitate the management. We have the model for smart light as below:

![smartlight Data model](../images/SmartLight.png "The data model of smartlight")




### 4.SCOPE AND DEVELOPMENT ORIENTATION 

**Now,** giving the model for emulated things based on generic sensors and actuators.Implementing emulated thing such as Smartlight…

**In future**, TEIT will emulated other common devices such as air conditioning, smartphone, tablet,...



