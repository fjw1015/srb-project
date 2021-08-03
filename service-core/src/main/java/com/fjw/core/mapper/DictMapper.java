package com.fjw.core.mapper;

import com.fjw.core.pojo.dto.ExcelDictDTO;
import com.fjw.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDTO> list);
}
