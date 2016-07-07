export BASE=`dirname $0`

if [ $# -lt 1 ]
then
        echo "Usage : $0 <command> start|stop|change-rate|connect-console|connect-mqtt"
        echo "Command:"
        echo "  start"
        echo "  stop"
        echo "  change-rate <rate>"
        echo "  connect-console"
        echo "  connect-mqtt <broker> [topic]"
        exit
fi

case "$1" in
 start)
   java -jar EmulatedSensor-1.0-SNAPSHOT.jar  > sensor.out 2>&1 &
   echo $!>sensor.pid
   ;;
   
 stop)
   kill `cat $BASE/sensor.pid`
   rm $BASE/sensor.pid
   echo "Sensor stopped sucessfully!"
   ;;
   
 change-rate)
   if [ -n "$2" -a "$2" != " " ]
   then
     NEW_VAL=$2
     sed -i 's#rate=.*#rate='$NEW_VAL'#' $BASE/sensor.conf 
     echo "Update rate set to $NEW_VAL mili-seconds!"
     touch sensor.conf
   else
     echo "No update rate provided!"
     exit 1
   fi
   ;;
   
 connect-console)
   sed -i 's#platform=.*#platform=teit.sensor.PlatformConsole.ConsolePlatform#' $BASE/sensor.conf
   touch sensor.conf   
   ;;
   
 connect-mqtt)
   sed -i 's#platform=.*#platform=teit.sensor.MQTT.MQTTOutput#' $BASE/sensor.conf
   sed -i 's#platform.mqtt.url=.*#platform.mqtt.url='$BROKER'#' $BASE/sensor.conf
   if [ ! -z "$3" ]; then
     sed -i 's#platform.mqtt.topic=.*#platform.mqtt.topic='$TOPIC'#' $BASE/sensor.conf
   fi
   touch sensor.conf
   ;;
   
esac
