package com.fjw.sms.client.fallback;

import com.fjw.sms.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fjw
 * @date 2021-07-22 00:10
 */
@Service
@Slf4j
public class CoreUserInfoClientFallback implements CoreUserInfoClient {


    @Override
    public boolean checkMobile(String mobile) {
        log.error("远程调用失败，服务熔断");
        //手机号不重复
        return false;
    }
}
