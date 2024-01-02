package com.btb.groupsservice.client.kafka;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class KafkaConstants {

    // misc
    public static final String SEPARATOR = ".";

    // topic items
    public static final String NOTIFICATIONS_TOPIC = "notifications";

    // commands
    public static final String GROUP_REQUEST = "group_request";

}

