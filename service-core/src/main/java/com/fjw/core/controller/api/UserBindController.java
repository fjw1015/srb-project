package com.fjw.core.controller.api;


import com.alibaba.fastjson.JSON;
import com.fjw.base.util.JwtUtils;
import com.fjw.common.result.R;
import com.fjw.core.hfb.RequestHelper;
import com.fjw.core.pojo.vo.UserBindVo;
import com.fjw.core.service.UserBindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 用户绑定表 前端控制器
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
@Api(tags = "会员账号绑定")
@RestController
@RequestMapping("/api/core/userBind")
@Slf4j
public class UserBindController {
    @Resource
    private UserBindService userBindService;

    @ApiOperation("账户绑定提交数据")
    @PostMapping("/auth/bind")
    public R bind(@RequestBody UserBindVo userBindVO, HttpServletRequest request) {
        //获取token 并对token进行校验 确保用户已经登录 并从token中获取userId
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        String formStr = userBindService.commitBindUser(userBindVO, userId);
        return R.ok().data("formStr", formStr);
    }

    @ApiOperation("账户绑定异步回调")
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        //汇付宝向尚融宝发起回调请求参数
        Map<String, Object> paramMap = RequestHelper.switchMap(request.getParameterMap());
        log.info("用户账号绑定异步回调接收的参数如下：" + JSON.toJSONString(paramMap));
        //校验签名
        if (!RequestHelper.isSignEquals(paramMap)) {
            log.error("用户账号绑定异步回调签名错误：" + JSON.toJSONString(paramMap));
            return "fail";
        }
        //修改绑定状态
        log.info("验证签名通过 开始进行绑定");
        userBindService.notify(paramMap);
        return "success";
    }

}

