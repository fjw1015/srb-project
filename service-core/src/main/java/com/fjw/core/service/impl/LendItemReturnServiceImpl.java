package com.fjw.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjw.core.mapper.LendItemMapper;
import com.fjw.core.mapper.LendItemReturnMapper;
import com.fjw.core.mapper.LendMapper;
import com.fjw.core.mapper.LendReturnMapper;
import com.fjw.core.pojo.entity.Lend;
import com.fjw.core.pojo.entity.LendItem;
import com.fjw.core.pojo.entity.LendItemReturn;
import com.fjw.core.pojo.entity.LendReturn;
import com.fjw.core.service.LendItemReturnService;
import com.fjw.core.service.UserBindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借回款记录表 服务实现类
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
@Service
public class LendItemReturnServiceImpl extends ServiceImpl<LendItemReturnMapper, LendItemReturn> implements LendItemReturnService {
    @Resource
    private LendMapper lendMapper;

    @Resource
    private LendReturnMapper lendReturnMapper;

    @Resource
    private LendItemMapper lendItemMapper;

    @Resource
    private UserBindService userBindService;

    @Override
    public List<LendItemReturn> selectByLendId(Long lendId, Long userId) {
        QueryWrapper<LendItemReturn> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("lend_id", lendId)
                .eq("invest_user_id", userId)
                .orderByAsc("current_period");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 通过还款计划的id，找到对应的回款计划数据，组装data参数中需要的List<Map>
     *
     * @param lendReturnId
     * @return
     */
    @Override
    public List<Map<String, Object>> addReturnDetail(Long lendReturnId) {
        //还款记录
        LendReturn lendReturn = lendReturnMapper.selectById(lendReturnId);
        //获取标的
        Lend lend = lendMapper.selectById(lendReturn.getLendId());
        List<LendItemReturn> lendItemReturnList = this.selectLendItemReturnList(lendReturnId);
        List<Map<String, Object>> lendItemReturnDetailList = new ArrayList<>();
        for (LendItemReturn lendItemReturn : lendItemReturnList) {
            //获取投资记录
            Long lendItemId = lendItemReturn.getLendItemId();
            LendItem lendItem = lendItemMapper.selectById(lendItemId);
            //获取投资人id
            Long investUserId = lendItem.getInvestUserId();
            String bindCode = userBindService.getBindCodeByUserId(investUserId);
            Map<String, Object> map = new HashMap<>();
            //项目编号
            map.put("agentProjectCode", lend.getLendNo());
            //投资编号
            map.put("voteBillNo", lendItem.getLendItemNo());
            //投资人bindCode
            map.put("toBindCode", bindCode);
            //还款总额
            map.put("transitAmt", lendItemReturn.getTotal());
            //本金
            map.put("baseAmt", lendItemReturn.getPrincipal());
            //利息
            map.put("benifitAmt", lendItemReturn.getInterest());
            map.put("feeAmt", new BigDecimal(0));
            lendItemReturnDetailList.add(map);
        }
        return lendItemReturnDetailList;
    }
    @Override
    public List<LendItemReturn> selectLendItemReturnList(Long lendReturnId) {
        QueryWrapper<LendItemReturn> lendItemReturnQueryWrapper = new QueryWrapper<>();
        lendItemReturnQueryWrapper.eq("lend_return_id", lendReturnId);
        return baseMapper.selectList(lendItemReturnQueryWrapper);
    }
}
