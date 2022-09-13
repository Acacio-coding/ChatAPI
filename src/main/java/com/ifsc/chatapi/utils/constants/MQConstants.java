package com.ifsc.chatapi.utils.constants;

public interface MQConstants {
    String USER_QUEUE_BASE = "users.v1.";
    String GROUP_EXCHANGE_BASE = "groups.v1.";
    String DIRECT_EXCHANGE_NAME = "amq.direct";
    String FAN_OUT_EXCHANGE_NAME = "amq.fanout";
}
