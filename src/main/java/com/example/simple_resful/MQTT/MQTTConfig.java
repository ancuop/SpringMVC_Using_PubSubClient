package com.example.simple_resful.MQTT;

/*
* Reference:
* http://www.monirthought.com/2017/11/eclipse-paho-java-client-mqtt-client.html
* */
public abstract class MQTTConfig {
    protected final String broker = "localhost";
    protected final int qos = 1;
    protected Boolean hasSSL = false;
    protected Integer port = 1883;
    protected final String username = "xxx";
    protected final String password = "xxx";
    protected final String TCP = "tcp://";
    protected final String SSL = "ssl://";

    /*
    * Custom configuration
    * */
    protected abstract void config(String broker, Integer port, Boolean ssl, Boolean withUsernameAndPass);

    /*
    * Default configuration
    * */
    protected abstract void config();
}
