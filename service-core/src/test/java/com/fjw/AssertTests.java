package com.fjw;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * @author fjw
 * @date 2021-07-10 16:46
 */
public class AssertTests {
    @Test
    public void test1() {
        Object o = null;
        if (o == null) {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Test
    public void test2() {
        Object o = null;
        //断言代替if解构
        Assert.notNull(o, "0参数错误");
    }


}
