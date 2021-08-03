package com.fjw.core.service.impl;

import com.fjw.core.pojo.entity.UserAccount;
import com.fjw.core.mapper.UserAccountMapper;
import com.fjw.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
