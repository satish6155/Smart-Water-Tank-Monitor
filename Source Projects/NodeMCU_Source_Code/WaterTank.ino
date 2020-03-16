#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>

  // Assign Arduino Friendly Names to GPIO pins
    #define D0 16  //NEVER USE    
    #define D3 0
    #define D4 2    //NEVER USE
    #define D5 14
    #define D6 12   //USE IT
    #define D7 13   //USE IT
    #define trigPin 5   //D1
    #define echoPin 4   //D2


/* Personal settings starts here */
const char* ssid = "YOUR_WIFI_NAME";
const char* password = "YOUR_WIFI_PASSWORD";

String baseUrl="http://192.168.225.28:8080/SpringRestHibernate/";
String clientId = "101";
String tankId = "191004001";
String getStatusMethod ="getTankBeanByTankId";
String updateStatusMethod = "updateTankByTankId";
int minWaterDepth = 5;
String statusByTank = "0";
String deviceNewStatus="I";
long currentWaterDepth ;
/* Personal settings ends here */

void setup () {

 Serial.begin(115200);
 pinMode(D6, OUTPUT);  
 pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
  delay(1000);
    Serial.println("Connecting..");
 
  } 
}
 
void loop() {
    
  checkstatusOfTank(10); 
  if(statusByTank=="1"){    //if tank is not overflow
    checkStatusByDB();      //check what status is present in DB
  }
  else{                       //If tank is overflow
    deviceNewStatus="0";      //turn off pump
    }
  updatePump();
  updateDbStatus();
  }

void updateDbStatus(){
     int attempts=1;
  while(WiFi.status() != WL_CONNECTED && attempts<=6){
    Serial.println("Connecting..again");
    WiFi.begin(ssid, password);
    attempts++;
    delay(2000);
    }
  if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status
    Serial.println("Connected..making request");
    String url=baseUrl+clientId+"/"+updateStatusMethod+"/"+tankId+"/"+deviceNewStatus+"/"+currentWaterDepth;
    Serial.println(url);
    HTTPClient http;   
    http.begin(url);     
    int httpCode = http.GET();    
    if (httpCode > 0) {       
      String payload = http.getString();   
      Serial.println(payload);       
  }
  }
  else {
    Serial.println("Unable to connect to server, skipping this process!" );
  }
}

void checkStatusByDB(){
     int attempts=1;
  while(WiFi.status() != WL_CONNECTED && attempts<=6){
    Serial.println("Connecting..again");
    WiFi.begin(ssid, password);
    attempts++;
    delay(2000);
    }
  if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status
    Serial.println("Connected..making request");
    String url=baseUrl+clientId+"/"+getStatusMethod+"/"+tankId;
    Serial.println(url);
    makeHttpRequest(url);
  }
  else {
    Serial.println("Unable to connect to server, skipping this process!" );
  }
}
void makeHttpRequest(String url){
  HTTPClient http;   
  http.begin(url);     
  int httpCode = http.GET();    
  if (httpCode > 0) {       
      String payload = http.getString();   
      Serial.println(payload);                     
      StaticJsonBuffer<300> JSONBuffer; 
      JsonObject& parsed = JSONBuffer.parseObject(payload);
       if (!parsed.success()) {   
            Serial.println("Parsing failed");
            delay(3000);
            return;
         }
       getStatusFromJason(parsed);
       http.end();
  
  }
}
 void getStatusFromJason(JsonObject& parsed){
     int tankId=parsed["tankId"];
      String waterLevel=parsed["waterLevel"];
      String pumpStatus=parsed["pumpStatus"];

      deviceNewStatus=pumpStatus;
     // Serial.println(tankId);
     // Serial.println(pumpStatus);
     //  updateDevices()  ; //call updateDevices() to change state of the devices
 }
 void updatePump(){
  Serial.println("Inside updateDevices" );
  if(deviceNewStatus.equals("1")){
    Serial.println("Inside if deviceNewStatus=1" );
    digitalWrite(D6,HIGH);
  }     
  if(deviceNewStatus.equals("0")){
    Serial.println("Inside if deviceNewStatus=0" );
    digitalWrite(D6,LOW);
  }
      
 }
void checkstatusOfTank(int accuracy){
     
long total_distance =0;
int count=1;
  while (count<=accuracy){
   // Serial.println("count:");
   // Serial.println(count);
    long duration, distance;
    digitalWrite(trigPin, LOW);  
    delayMicroseconds(2); 
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10); 
    digitalWrite(trigPin, LOW);
    duration = pulseIn(echoPin, HIGH);
    distance = (duration/2) / 29.1;
   // Serial.println("distance:");
   // Serial.println(distance);
    if(distance>0 && distance <225){
      total_distance = total_distance+distance;
      count++;
    }
    delay(200);
  }
  currentWaterDepth = total_distance/accuracy;  
  Serial.println("currentWaterDepth is :"); 
   Serial.println(currentWaterDepth);

   if(currentWaterDepth<=minWaterDepth){
     statusByTank="0";
   }
   else{
     statusByTank="1";
   }
    
}
