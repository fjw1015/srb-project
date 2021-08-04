package com.fjw.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjw.common.exception.Assert;
import com.fjw.common.result.ResponseEnum;
import com.fjw.core.hfb.FormHelper;
import com.fjw.core.hfb.HfbConst;
import com.fjw.core.hfb.RequestHelper;
import com.fjw.core.mapper.UserAccountMapper;
import com.fjw.core.mapper.UserInfoMapper;
import com.fjw.core.pojo.entity.UserAccount;
import com.fjw.core.pojo.entity.UserInfo;
import com.fjw.core.service.UserAccountService;
import com.fjw.core.util.LendNoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
@Service
@Slf4j
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public String commitCharge(BigDecimal chargeAmt, Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        String bindCode = userInfo.getBindCode();
        //判断账户绑定状态
        Assert.notEmpty(bindCode, ResponseEnum.USER_NO_BIND_ERROR);

        Map<String, Object> paramMap = new HashMap<>();
        //商户号 agentId
        paramMap.put("agentId", HfbConst.AGENT_ID);
        //充值编号
        paramMap.put("agentBillNo", LendNoUtils.getChargeNo());
        //绑定协议号
        paramMap.put("bindCode", bindCode);
        //充值金额
        paramMap.put("chargeAmt", chargeAmt);
        //商户收取用户的手续费
        paramMap.put("feeAmt", new BigDecimal("0"));
        //检查常量是否正确
        paramMap.put("notifyUrl", HfbConst.RECHARGE_NOTIFY_URL);
        paramMap.put("returnUrl", HfbConst.RECHARGE_RETURN_URL);
        paramMap.put("timestamp", RequestHelper.getTimestamp());
        String sign = RequestHelper.getSign(paramMap);
        paramMap.put("sign", sign);

        //构建充值自动提交表单
        String formStr = FormHelper.buildForm(HfbConst.RECHARGE_URL, paramMap);
        return formStr;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String notify(Map<String, Object> paramMap) {
        log.info("充值成功：" + JSONObject.toJSONString(paramMap));
        //充值人绑定协议号
        String bindCode = (String) paramMap.get("bindCode");
        //充值金额
        String chargeAmt = (String) paramMap.get("chargeAmt");

        //优化
        baseMapper.updateAccount(bindCode, new BigDecimal(chargeAmt), new BigDecimal(0));

        //增加交易流水
        //TODO

        return "success";
    }
}
