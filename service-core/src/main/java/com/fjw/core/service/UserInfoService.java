package com.fjw.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjw.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjw.core.pojo.query.UserInfoQuery;
import com.fjw.core.pojo.vo.LoginVO;
import com.fjw.core.pojo.vo.RegisterVo;
import com.fjw.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVo registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);


    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id, Integer status);

    boolean checkMobile(String mobile);
}
