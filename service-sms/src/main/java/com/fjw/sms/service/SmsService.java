package com.fjw.sms.service;

import java.util.Map;

/**
 * @author fjw
 * @date 2021-07-17 00:40
 */
public interface SmsService {
    void send(String mobile, String templateCode, Map<String, Object> param);
}
