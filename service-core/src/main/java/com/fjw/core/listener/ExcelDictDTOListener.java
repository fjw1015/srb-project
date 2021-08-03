package com.fjw.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fjw.core.mapper.DictMapper;
import com.fjw.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fjw
 * @date 2021-07-14 22:36
 * NoArgsConstructor 无参
 */
//@AllArgsConstructor //全参
@Slf4j
@NoArgsConstructor //无参
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {

    /**
     * 先自己设置每隔5条存储数据库，在实际开发中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    /**
     * 数据列表
     */
    List<ExcelDictDTO> list = new ArrayList();

    private DictMapper dictMapper;

    /**
     * 传入mapper对象 通过构造函数注入  没有使用@Component
     */
    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    /**
     * 遍历每一行的记录
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext context) {
        log.info("解析到一条记录: {}", data);
        //将数据存入数据列表
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            //调用mapper层的save方法
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据， 当最后剩余的数据记录数不足BATCH_COUNT时，再次存储一次
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        //批量插入
        dictMapper.insertBatch(list);
        log.info("存储数据库成功！");
    }
}
