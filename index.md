---
layout: default
title: "TEIT, A Toolset for Emulated IoT Testbed"
---


### 1. PROBLEM

We consider a scenario of a company that provide Smart Building services. Its customers can be high buildings or hotels in the city. To build the such system, the company needs to build several types of services, which can be summarized as below:

* To sense and control the environment (Heating, ventilating, air, cooling, light, etc). The need information about temperature, humidity,  concentration of CO2, brightness of each room, etc.

* To interact with human activity. They need information about the presence of people by detecting movements, light interacting, doors opening, card scanning, etc.

* To notify or to send alarm in emergency. They need information about smoke level, fire detection, electronic failures and energy decrease detecting, etc.

However, at the development phase of the above services, app developers need an emulation environment for testing and developing services. They create sensors and generate corresponding data with circumstances. Emulation system can be deployed on Cloud by running emulation sensors/actuators, sending data to services, services feedback controlling messages to actuators.

We aim to develop an Emulation system, which is different with a Simulation system. The differences between Emulation system and Simulation system as bellow:
 
| Characteristics  | Emulation  |  Simulation |
|---|---|---|
|  Overview |   Like the system in reality (exactly)|  Similar with real system |
|Operation principles   |  Replicating internal operation of the system  |   Replicating external behaviors of the system |
|   Resources| Running in the environment that is different from real the environment of real system   |Running inside application, in closed and secure environment   |
|  Usage |  To be used Instead of real system  | To be used for learning and studying  |
|  Experiment results |  Practical because of the effects from real systems, more interesting |  Results look nice and predictable |
|  Deployment |  Hardly  |  Easily (single click) |

### 2. PURPOSE
TEIT project towards to providing basic components to build an emulation testbed to support the development process of the system mentioned above. This project includes multiple small tools as below:

* **Emulated Sensor:** enables developers to emulate real sensors that sense the environment and provide sensing data. The data can come from different emulated sources (such as log file in CSV format). Emulated sensors is highly configurable to read from different inputs and write to various outputs.	

* **Emulated Actuator:** enables developers to emulate real actuators that control and manage states. Emulated Actuator exposes easy-to-use and dynamic APIs.

* **Emulated Things:** enables developers to emulate complex devices by composing many sensors and actuators. Emulated thing provides an easy way to generate IoT devices and unified APIs for the device management.

* **Emulated Gateway:** Providing environment in order to pack and deploy IoT services, expected to deploy on Docker (e.g. User has service: “If light is turned on over 5 minutes”, and needing to deploy on this gateway. Gateway plays a role as data aggregator with MQTT on it.

* **Emulated Network:** it could use some simulation tool like Mininet(http://mininet.org/) in order to virtual network, serving for finding way and optimization in media.

* **Emulated Environment:** Generating data for sensors based on interactions of objects. For example, environment is a room, consisting of 4 corners (c1...c4), in each corner, there is a temperature sensor. The temperatures of four corners in the room depends on position of Air conditioning and window. For example,when opening window, the temperatures of 4 corners increase 1 degree per minute to the boundary value 35 degree, air conditioning reduces the temperatures of 2 corners 1.5 degree per minute at one time  to the boundary value 25 degree.

* **Script to generate above resources:** Orienting user through a Wizard or interactive script to give input and configuration. Output are artifacts to run emulation resources above. 

### 3. REQUIREMENT

From real problem above, we outline the requirements for TEIT project as below: 

* **General tools emulate a lot of scenarios**. First, focusing on static scenarios (e.g. home automation, building automation,...), then, gradually shifting to dynamic scenario (e.g. traffic, fleet management, battlefield,...)

* **Extensibility:** clear interfaces that could be added more new features

* **Usability:** quick and simple configuration. Tools need “small and beauty”  and give user a lot of options.

* **In future**, it could combine with other tool (SALSA) in order to give standard templates. For example, user needs a template for a 10-storey building, 5 rooms/floor, XYZ sensors/room, it could be generate a testbed quickly.
These requirements will be used to evaluate the system and to guide the development process.

### 4. EVALUATION METHODS

* **Features:** looking for and setting up an experiment system with a variety of sensors (e.g. Smart  house with many kinds of devices). Evaluating usability, configuration, high customization and ability of quick deployment system.

* **Performance:** looking for and setting up an experiment systems with a huge of resources (e.g. traffic with bulk vehicles, high fluctuation)

* **Evaluating based on stability of testbed:** deploying on many computers and a lot of sensors running at the same time, the number of bugs (due to emulation toolset and environment)
 
* **Evaluating performance** based on specific problem.

### 5. SOURCES

    https://github.com/EllaPham/TEIT 
	
### 6. PEOPLE

* Phạm Thị Như Trang (main contact)
* Lê Đức Hùng
* Nguyễn Bình Minh
* Nguyễn Hữu Đức

For any kind of queries, please contact <trangptn.hut@gmail.com>
This project is developing at Hanoi University of Science and Technology (HUST).																										
