---
layout: page
title: "Emulated Sensor"
category: doc
date: 2016-06-27 11:11:17
order: 1
---

2. Emulated Sensor
2.1. Introduction
Emulated Sensor mô phỏng hoạt động của các sensor thực, bao gồm 3 bước.

Bước 1, sensor thu nhận dữ liệu từ môi trường, ví dụ nhiệt độ, độ ẩm, thông số thiết bị. Các dữ liệu thu thập thường ở các định dạng khác nhau, ví dụ tên trường, kiểu dữ liệu (cùng là nhiệt độ, dữ liệu có thể là C hoặc F, tên trường là temperature, temp) .

Bước 2, sensor chuyển đổi dữ liệu đặc trưng của môi trường thành định dạng chuẩn.

Bước 3, sensor gửi dữ liệu đến các platform hoặc đầu ra khác. Bước này cần sự chuyển đổi dữ liệu từ định dạng chuẩn sang định dạng đầu ra phù hợp.

2.2. Architecture
Data —(InputAdaptor) —> Emulated Sensor ----(OutputAdaptor)—> Providers T I sensor.conf

Data:dữ liệu thực được thu thập từ sensor thực với các định dạng khác nhau.
Input Adaptor: có nhiệm vụ chuyển từ dữ liệu sensor thực từ các định dạng khác nhau thành 1 định dạng duy nhất. Dữ liệu chuẩn này này có dạng: Map<String,String> OutputAdaptor: Có nhiệm vụ chuyển từ dữ liệu đầu ra duy nhất của Emulated Sensor thành dữ liệu theo định dạng ứng với từng provider cụ thể.

Việc lựa chọn kiểu dữ liệu đầu vào và provider được cấu hình trong 1 file configure có tên là “Sensor.conf”. Cấu trúc của file sensor.conf bao gồm: *Cấu hình cho Emulated Sensor *Cấu hình cho Input: *Cấu hình cho Output:

2.3. Input adaptor
2.3.1. CSV Adaptor
Đầu vào cơ bản nhất của Emulated Sensor là các file CSV. Mục đích của việc dùng file CSV là mô phỏng lại một kịch bản đã có. Ví dụ, người dùng cần mô phỏng lại nhiệt độ của phòng trong một ngày, dữ liệu này đã được ghi lại (log) trong file CSV. CSV là một file text có cấu trúc như sau: Dòng đầu tiên chứa tên các trường (header) Các dòng tiếp theo lưu trữ các bản ghi dữ liệu (record), mỗi bản ghi 1 dòng Các cột dữ liệu phân cách nhau bằng dấu phảy Mặc định Emulated Sensor sẽ đọc file “sensor.data” tại thư mục hiện thời CSV Adaptor sẽ đọc dòng đầu tiên để lấy ra tên các cột dữ liệu. Sau mỗi chu kỳ, sensor đọc 1 dòng tiếp theo trong file CSV. Các cột dữ liệu trong bản ghi được gán tương ứng với tên các trường trong header.

Ví dụ: file csv tên là “sensor.data”

timestamp,cpu,ram,computername 20052016,1.3,500,TRANGPC
20052016,1.5,600,TRANGPC
20052016,1.6,800,TRANGPC 20052016,1.4,900,TRANGPC
20052016,1.3,100,TRANGPC
20052016,1.2,200,TRANGPC 20052016,1.6,300,TRANGPC 20052016,1.9,400,TRANGPC

Sau mỗi chu kỳ, dữ liệu được đẩy ra dưới dạng Map như sau: {timestamp=20052016, ram=600, cpu=1.5, computername=TRANGPC, sensorid=123}

2.3.2. ConsoleData adaptor
User có thể nhập dữ liệu trực tiếp từ bàn phím

2.3.3. Laptop data adaptor
Dữ liệu của user, cụ thể phần này là dữ liệu lấy từ sensor của laptop đo ram, cpu, computername. Qua ví dụ này, ta có thể mở rộng việc lấy dữ liệu thực qua Emulated Sensor. Giả sử ta có N sensor gửi N điểm dữ liệu. Trên gateway, 1 Emulated Sensor sẽ thu thập tất cả dữ liệu thành 1 định dạng. Ví dụ trong phòng có 3 sensor nhiệt độ, 1 sensor báo cháy, 1 sensor độ ẩm. Tất cả các sensor này được truyền qua Emulated Sensor thành 4 điểm dữ liệu, biểu thị điều kiện hiện tại của phòng.

2.4. Output adaptor:
2.4.1.ConsolePlatform
Dữ liệu thực của sensor được nhập trực tiếp từ bàn phím, sau đó được in ra màn hình console
2.4.2.Sparkfun Platform
Link url: https://data.sparkfun.com/ 
Dữ liệu đọc từ sensor được gửi lên IoT platform sparkfun thông qua Sparkfun APIs

2.4.3. Thingspeak
Link url: https://thingspeak.com/ 
Dữ liệu đọc từ sensor được gửi lên IoT platform thingspeak thông qua Thingspeak APIs

2.5. Usage
2.5.1.Cấu hình cho Emulated sensor thông qua 1 file cấu hình duy nhất “sensor.conf”
Cấu hình trong file configure đơn giản, rõ ràng và linh hoạt a.Configure cho sensor *rate: user còn có thể upload dữ liệu từ emulated sensor lên các đầu ra liên tục, thời gian giữa các lần upload cũng được setting ở trong sensor.conf, thông qua tham số “rate” (ex: rate = 5000) *sensorID: b.Configure cho Input: Cấu hình định dạng cho data đầu vào của Emulated Sensor CSV file: data: data=teit.sensor.CSVFile.CSVDataAdaptor Dữ liệu nhập từ bàn phím: data=teit.sensor.PlatformConsole.ConsoleData Customer data: data=teit.sensor.PlatformSparkFun.LaptopData (Sparkfun platform) data=teit.sensor.PlatformThingSpeak.ThingSpeakData (Thingspeak platform)

c.Configure cho Output: Cấu hình nơi Emulated Sensor gửi dữ liệu tới Console Platform: platform=teit.sensor.PlatformConsole.ConsolePlatform Thingspeak Platform: platform=teit.sensor.PlatformThingSpeak.ThingSpeakPlatform Sparkfun Platform: platform=teit.sensor.PlatformSparkFun.SparkfunPlatform MQTT: platform=teit.sensor.MQTT.MQTTOutput

2.5.2. Run Emulated sensor:
Sau khi chuẩn bị dữ liệu và cấu hình trong sensor.conf Trong command line, gõ dòng lệnh sau để chạy:

java -jar target\EmulatedSensor-1.0-SNAPSHOT.jar