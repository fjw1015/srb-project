package com.fjw.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjw.core.pojo.entity.BorrowInfo;

import java.util.List;

/**
 * <p>
 * 借款信息表 Mapper 接口
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {

    List<BorrowInfo> selectBorrowInfoList();
}
