package com.example.simple_resful.MQTT;

public interface MQTTPublisherBase {
    /*
    * Publish message
    * */
    public void publishMessage(String topic, String message);

    /*
    * Disconnect MQTT client
    * */
    public void disconnect();
}
