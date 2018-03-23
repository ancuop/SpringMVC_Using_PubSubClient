package com.example.simple_resful.MQTT;

public interface MQTTSubscriberBase {
    /*
    * Subscribe message
    * */
    public void subscribeMessage(String topic);
    /*
    * Disconnect to MQTT server
    * */
    public void disconnect();
}
