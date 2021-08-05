package com.fjw.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fjw.core.pojo.bo.TransFlowBO;
import com.fjw.core.pojo.entity.TransFlow;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
public interface TransFlowService extends IService<TransFlow> {
    void saveTransFlow(TransFlowBO transFlowBO);

    boolean isSaveTransFlow(String agentBillNo);
}
