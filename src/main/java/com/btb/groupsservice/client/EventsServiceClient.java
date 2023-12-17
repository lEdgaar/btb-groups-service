package com.btb.groupsservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EventsServiceClient {

    private final String EVENTS_SERVICE_URL = "http://localhost:9004/events";

    public void createEvent() {
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = EVENTS_SERVICE_URL + "/";

        restTemplate.postForEntity(endpoint, null, String.class);
    }

}
