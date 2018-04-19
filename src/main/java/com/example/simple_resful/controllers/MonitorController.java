package com.example.simple_resful.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/*
* Follow:
*       https://o7planning.org/vi/11757/giam-sat-ung-dung-voi-spring-boot-actuator
*       http://www.baeldung.com/spring-boot-actuators
* */
@Controller
public class MonitorController {

    @ResponseBody
    @RequestMapping(path = "/admin")
    public String admin(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String host = request.getServerName();
        String endpointBasePath = "/actuator";

        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Spring boot actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        String url = "http://" + host + ":8090" + contextPath + endpointBasePath;
        System.out.println("### Actuator url: " + url);
        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");
        sb.append("</ul>");
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(path = "/admin/shutdown")
    public String shutdown() {
        String url = "http://localhost:8090/actuator/shutdown";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate template = new RestTemplate();
        HttpEntity<String> responseBody = new HttpEntity<>("", headers);
        String e = template.postForObject(url, responseBody, String.class);
        return "Result: " + e;
    }

}
