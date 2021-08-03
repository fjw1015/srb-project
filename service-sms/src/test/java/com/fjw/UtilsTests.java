package com.fjw;

import com.fjw.sms.ServiceSmsApplication;
import com.fjw.sms.util.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fjw
 * @date 2021-07-17 00:27
 */
@SpringBootTest(classes = ServiceSmsApplication.class)
@RunWith(SpringRunner.class)
public class UtilsTests {

    @Test
    public void testProperties() {
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.REGION_Id);
    }
}