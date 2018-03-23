package com.example.simple_resful.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class MQTTSubscriber extends MQTTConfig implements MqttCallback, MQTTSubscriberBase {

    private String brokerUrl = null;
    final private String colon = ":";
    final private String clientId = "demoClient2";

    private MqttClient mqttClient = null;
    private MqttConnectOptions connectionOptions = null;
    private MemoryPersistence persistence = null;

    private static final Logger logger = LoggerFactory.getLogger(MQTTSubscriber.class);


    public MQTTSubscriber() {
        this.config();
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("### Connection Lost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String time = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("Message arrived at: " + time + " Topic" + topic
                + " Message: " + new String(mqttMessage.getPayload()));
        System.out.println("*******************************************************");
        System.out.println();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // leave it blank for subscribe
    }

    @Override
    public void subscribeMessage(String topic) {
        try {
            this.mqttClient.subscribe(topic);
        } catch (MqttException e) {
            logger.error("### ERROR", e);
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
    protected void config(String broker, Integer port, Boolean ssl, Boolean withUsernameAndPass) {
        String protocal = this.TCP;
        if (true == ssl) {
            protocal = this.SSL;
        }

        this.brokerUrl = protocal + this.broker + colon + port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();

        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            if (true == withUsernameAndPass) {
                if (this.password != null) {
                    this.connectionOptions.setPassword(this.password.toCharArray());
                }
                if (this.username != null) {
                    this.connectionOptions.setUserName(this.username);
                }
            }
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }

    @Override
    protected void config() {
        this.brokerUrl = this.TCP + this.broker + colon + this.port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();
        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }

}
