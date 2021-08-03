package com.fjw.core.service;

import com.fjw.core.pojo.entity.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjw.core.pojo.vo.UserBindVo;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
public interface UserBindService extends IService<UserBind> {
    /**
     * 账户绑定提交到托管平台的数据
     */
    String commitBindUser(UserBindVo userBindVO, Long userId);

    void notify(Map<String, Object> paramMap);
}
