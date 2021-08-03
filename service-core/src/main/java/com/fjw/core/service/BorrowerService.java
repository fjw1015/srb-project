package com.fjw.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjw.core.pojo.entity.Borrower;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjw.core.pojo.vo.BorrowerApprovalVO;
import com.fjw.core.pojo.vo.BorrowerDetailVO;
import com.fjw.core.pojo.vo.BorrowerVO;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author fjw
 * @since 2021-07-10
 */
public interface BorrowerService extends IService<Borrower> {

    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(Long userId);

    IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword);

    BorrowerDetailVO getBorrowerDetailVOById(Long id);

    void approval(BorrowerApprovalVO borrowerApprovalVO);
}
