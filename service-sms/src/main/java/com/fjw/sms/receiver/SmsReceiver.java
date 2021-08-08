package com.fjw.sms.receiver;

import com.fjw.base.dto.SmsDTO;
import com.fjw.rabbitutil.constant.MQConst;
import com.fjw.sms.service.SmsService;
import com.fjw.sms.util.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fjw
 * @date 2021-08-08 18:10
 */
@Component
@Slf4j
public class SmsReceiver {
    @Resource
    private SmsService smsService;

    /**
     * RabbitListener  QueueBinding队列绑定器 value队列 exchange交换机 key路由
     *
     * @param smsDTO
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MQConst.QUEUE_SMS_ITEM, durable = "true"),
            exchange = @Exchange(value = MQConst.EXCHANGE_TOPIC_SMS),
            key = {MQConst.ROUTING_SMS_ITEM}
    ))
    public void send(SmsDTO smsDTO) throws IOException {
        log.info("SmsReceiver 消息监听");
        Map<String, Object> param = new HashMap<>();
        //code是短信模板的${code}
        param.put("code", smsDTO.getMessage());
        smsService.send(smsDTO.getMobile(), SmsProperties.TEMPLATE_CODE, param);
    }
}