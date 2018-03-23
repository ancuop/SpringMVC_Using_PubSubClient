package com.example.simple_resful.controllers;

import com.example.simple_resful.MQTT.MQTTPublisherBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    @Autowired
    MQTTPublisherBase publisher;

    @RequestMapping(value = "/mqtt/send", method = RequestMethod.POST)
    public String index(@RequestBody String data) {
        publisher.publishMessage("esp_sub", data);
        return "Message sent to broker";
    }

}
