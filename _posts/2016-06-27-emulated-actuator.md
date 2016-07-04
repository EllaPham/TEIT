---
layout: page
title: "Emulated Actuator"
category: doc
date: 2016-06-27 11:11:28
order: 2
---
### 1. INTRODUCE

#### 1.1.MOTIVATION

Like a sensor, an actuator is a basic part of  IoT system, focus on the control part of the system. Implementing Emulated Sensor is applied in developing IoT system. 

#### 1.2. OVERVIEW

Emulated Actuator is designed based on the operation principles of real actuator. These operation principles as below:

* Actuator has a set of state values, e.g. on/off 

* Actuator has a set of control actions, e.g. turn-on/turn-off

* From each state that executes a set of actions, e.g. state “off” can turn-on 

* Each action can change state from current state to another state, e.g.  turn-off changes on -> off 

* Don’t exist 2 actions that can changes from the same starting state to destination state

### 2. ARCHITECTURE

#### 2.1. PROCESS

![Actuator Process](../images/ActuatorProcess.png "The process of actuator")

* **Description file:** including descriptions for an actuator. In this file, there have states and a set of control actions. Description file can be in a variety of formats (e.g. json, xml, csv,...). In this project, in order to easy to read and write, we choose json format for description file. 

* **API state & control:** being a collection of APIs in order to call control actions of the actuator. these APIs are generated depending on each type of actuator (from description file)

#### 2.2. DATA MODEL

![Data Model](../images/ActuatorDataModel.png "The data model of actuator")

### 3. DESCRIPTION FILE

Description file is input for “EmumActutor.jar”. The content of this file as below: 

* **stateList:** a set of state values of the actuator

* **currentState:** state at the current time 

* **controlList:** a set of control actions. Each record of this collection includes fields as below: 

 * **Name:** name of control action 

 * **startState:** starting state 

 * **endState:** ending state 

 * **parameter:** other parameters

Json file is generated from Generator classes. Depending on each actuator of user, the application provides for user a Generator class corresponding to that actuator.

Emulated Actuator has done 3 generator classes corresponding to 2 types of actuator. We will show as below

#### 3.1. SWITCH ACTUATOR 

* **Including 2 states:**   ON/OFF

* **State Machine:** 

![switch machine](../images/SwitchMachine.png "The switch state machine")

An example about  json file for switch actuator  "actuator.data" as below:



    {
      "description" : "Switch",
      "states" : [ "ON", "OFF"],
      "controls" : [ {
        "name" : "turn-on",
        "startState" : "OFF",
        "endState" : "ON",
        "parameter" : null
      }, {
        "name" : "turn-off",
        "startState" : "ON",
        "endState" : "OFF",
        "parameter" : null
      } ],
      "currentState" : null,
      "stateList" : [ "ON", "OFF" ],
      "controlList" : [ {
        "name" : "turn-on",
        "startState" : "OFF",
        "endState" : "ON",
        "parameter" : null
      }, {
        "name" : "turn-off",
        "startState" : "ON",
        "endState" : "OFF",
        "parameter" : null
      } ]
    }

#### 3.2.DOOR ACTUATOR 

* **Including 3 states:**  OPENED/ CLOSED/ LOCKED

* **State Machine:**
		
![Door Switch](../images/DoorSwitch.png "The door state machine")
		

An example about  json file for door actuator  "actuator.data" as below:

    {
      "description" : "door_control",
      "states" : [ "OPENED", "CLOSED", "LOCKED" ],
      "controls" : [ {
        "name" : "open-door",
        "startState" : "CLOSED",
        "endState" : "OPENED",
        "parameter" : null
      }, {
        "name" : "close-door",
        "startState" : "OPENED",
        "endState" : "CLOSED",
        "parameter" : null
      }, {
        "name" : "lock-door",
        "startState" : "CLOSED",
        "endState" : "LOCKED",
        "parameter" : null
      }, {
        "name" : "unlock-door",
        "startState" : "LOCKED",
        "endState" : "CLOSED",
        "parameter" : null
      } ],
      "currentState" : "LOCKED",
      "stateList" : [ "OPENED", "CLOSED", "LOCKED" ],
      "controlList" : [ {
        "name" : "open-door",
        "startState" : "CLOSED",
        "endState" : "OPENED",
        "parameter" : null
      }, {
        "name" : "close-door",
        "startState" : "OPENED",
        "endState" : "CLOSED",
        "parameter" : null
      }, {
        "name" : "knock-door",
        "startState" : "CLOSED",
        "endState" : "KNOCKED",
        "parameter" : null
      }, {
        "name" : "unknock-door",
        "startState" : "KNOCKED",
        "endState" : "CLOSED",
        "parameter" : null
      } ]
    }

### 4. USAGE

    command --> (json -> actuator -> API) --> state 
    
After using Generator to create json file. User runs command line to emulate operation of the actuator: 

    java -jar target\EmulatedActuator-1.0-SNAPSHOT.jar                                                      + [command]

* **[Command]** as below:

 * **state-list:** showing a collection of state values

 * **current-state:** showing the state value at the current time 

 * **action-list:** showing a set of control actions 

 * <action-name>: executing actions. ex: 

  * turn-off: changing state from “ON” to “OFF”

  * turn-on: changing state from “OFF” to “ON”

The following picture are the result when running “EmumActuator.jar”

![Door Switch](../images/DoorSwitchExperiment.png "The actuator expriment")




