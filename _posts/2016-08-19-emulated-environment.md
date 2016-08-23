---
layout: page
title: "Emulated Env"
category: doc
date: 2016-08-19 14:12:31
order: 4
---


### 1.INTRODUCE

#### 1.1. MOTIVATION

In a IoT testbed, preparing data is one of the most important parts that decides the quality of the testbed. In order to generate required data, current approaches are mostly based on replaying historical data. However, this approach cannot provide customized scenario or simulated special cases which rarely happen in real life.

From above demand, there needs a tool supporting user in data description method and data generating based on user’s particular scenario. These scenarios can be specified by a set of generation rules and store in a text file.

Thus, Emulated Environment tool is designed and implemented for supporting users in specifying their rules and generating data based on a set of predefined rules and parameters. With this tool, generating data becomes easier, more convenient and more customization.

#### 1.2. OVERVIEW

Emulated Environment is a tool for generating data based on users’ rules. This specification file is stored in a text file, providing an unified format for user to edit type their generating data rules

When using Emulated Environment tool, users could be easy to generate data for their scenarios.

### 2.ARCHITECTURE

#### 2.1.PROCESS

![EmulatedEnv Process](../images/EmulatedEnvProcess.png "The process of emulated environment")

**Generation rule description:** an unified format (.txt) is provided by TEIT, allowing users to type their data generation rules.

**Emulated Environment module:** reading “Generation rule description” file and generating data 

**Data:** generated based on Generation rule description

Data could be read by Virtual Sensor and sent to IoT platforms and MQTT

#### 2.2.DATA MODEL

The model of Emulated Environment module as below: 

![EmulatedEnv Model](../images/EmulatedEnvDataModel.png "The model of emlulated environment")

**Condition plaintext file** is Generation rule description file, the input file of Emulated Environment module with .txt format. In this file, users could write their rules for generating data. These rules are called a rule-set. TEIT provides  an unified format that allows users typing their rules. Each rule includes 2 elements: **condition and function**. 

    Rulename->ConditionName:param1:param2..->FunctionName:param1:param2:....

 An example of Condition plaintext file as below:

    rule1->interval:1000->bouncing:10:30:11:2
    rule2->interval:2000->bouncing:40:80:45:3
    rule3->interval:3000->bouncing:80:100:81:1

The main elements of rule-set consist of: 

**GenRule:** generating data based on Condtion and function

**GenCondition:** reading condition from “Condition plaintext file” 

**GenFunction:** reading funcition from from “Condition plaintext file” 

The rules in a rule-set are combined in generating process. The method of combination is mentioned: **Random selected condition**. This method includes the following steps: 

* Selecting satisfied conditions and adding them to a consideration list. 

* All functions of the rules in the consideration list are executed.

* Choosing a rule in the consideration list by random algorithm.

* The result of a data generation turn is the result of the executing of the function corresponding in step 3

TEIT provides some functions for generating data: 

* RandomData

* Bouncing

* Error

* Incomplete

* Exception

### 3. SCOPE & ORIENTATION DEVELOPMENT

**Now,** TEIT provides generating data for temperature sensor, generating data every N(s) and supporting some functions for generating.

**In future,** TEIT will develop generating data for other sensors and supporting more functions such as error, incomplete, exception….

