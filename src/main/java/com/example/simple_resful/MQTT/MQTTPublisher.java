package com.example.simple_resful.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MQTTPublisher extends MQTTConfig implements MQTTPublisherBase, MqttCallback {

    private String brokerURL = null;

    final private String colon = ":";
    final private String clientId = "demoClient1";

    private MqttClient mqttClient = null;
    private MqttConnectOptions connectOptions = null;
    private MemoryPersistence memoryPersistence = null;

    private static final Logger logger = LoggerFactory.getLogger(MQTTPublisher.class);

    /*
    * Default constructor
    * */
    private MQTTPublisher() {
        this.config();
    }

    /*
    * Private constructor
    * */
    private MQTTPublisher(String broker, Integer port, Boolean ssl, Boolean withUsernameAndPass) {
        this.config(broker, port, ssl, withUsernameAndPass);
    }

    /*
    * Factory method to get instance of MQTTPublisher
    * */
    public static MQTTPublisher getInstance() {
        return new MQTTPublisher();
    }

    /*
    * Factory method to get instance of MQTTPublisher
    * */
    public static MQTTPublisher getInstance(String broker, Integer port, Boolean ssl, Boolean withUsernameAndPass) {
        return new MQTTPublisher(broker, port, ssl, withUsernameAndPass);
    }


    @Override
    protected void config() {
        this.brokerURL = this.TCP + this.brokerURL + this.colon + this.port;
        this.memoryPersistence = new MemoryPersistence();
        this.connectOptions = new MqttConnectOptions();

        try {
            this.mqttClient = new MqttClient(this.brokerURL, this.clientId, this.memoryPersistence);
            this.connectOptions.setCleanSession(true);
            this.mqttClient.connect(connectOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException e) {
            logger.error("### ERROR: config: " + e);
        }
    }

    @Override
    protected void config(String broker, Integer port, Boolean ssl, Boolean withUsernameAndPass) {
        String protocol = this.TCP;
        if (ssl == true) {
            protocol = this.SSL;
        }
        this.brokerURL = protocol + this.broker + this.colon + this.port;
        this.memoryPersistence = new MemoryPersistence();
        this.connectOptions = new MqttConnectOptions();

        try {
            this.mqttClient = new MqttClient(this.brokerURL, this.clientId, this.memoryPersistence);
            this.connectOptions.setCleanSession(true);
            if (withUsernameAndPass == true) {
                if (this.username != null) {
                    this.connectOptions.setUserName(this.username);
                }
                if (this.password != null) {
                    this.connectOptions.setPassword(this.password.toCharArray());
                }
            }
            this.mqttClient.connect(this.connectOptions);
            this.mqttClient.setCallback(this);

        } catch (MqttException e) {
            logger.error("### ERROR: config customize: " + e);
        }
    }

    @Override
    public void publishMessage(String topic, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(this.qos);
            this.mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            logger.error("### ERROR: publishMessage: " + e);
        }


    }

    @Override
    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException e) {
            logger.error("### ERROR", e);
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("### Connection Lost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        // leave this blank for publisher
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("### delivery completed");
    }
}
