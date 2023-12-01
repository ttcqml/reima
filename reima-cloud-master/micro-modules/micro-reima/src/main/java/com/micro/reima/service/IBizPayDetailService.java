package com.micro.reima.service;

import com.micro.reima.model.pos.TransactionTender;
import java.util.List;
import com.micro.reima.domain.BizPayDetail;

/**
 * 支付明细Service接口
 * 
 * @author micro
 * @date 2022-01-12
 */
public interface IBizPayDetailService 
{
    public void saveBizPayDetail(List<TransactionTender> details,Long orderId);
    /**
     * 查询支付明细
     * 
     * @param id 支付明细主键
     * @return 支付明细
     */
    public BizPayDetail selectBizPayDetailById(Long id);

    /**
     * 查询支付明细列表
     * 
     * @param bizPayDetail 支付明细
     * @return 支付明细集合
     */
    public List<BizPayDetail> selectBizPayDetailList(BizPayDetail bizPayDetail);

    /**
     * 新增支付明细
     * 
     * @param bizPayDetail 支付明细
     * @return 结果
     */
    public int insertBizPayDetail(BizPayDetail bizPayDetail);

    /**
     * 修改支付明细
     * 
     * @param bizPayDetail 支付明细
     * @return 结果
     */
    public int updateBizPayDetail(BizPayDetail bizPayDetail);

    /**
     * 批量删除支付明细
     * 
     * @param ids 需要删除的支付明细主键集合
     * @return 结果
     */
    public int deleteBizPayDetailByIds(Long[] ids);

    /**
     * 删除支付明细信息
     * 
     * @param id 支付明细主键
     * @return 结果
     */
    public int deleteBizPayDetailById(Long id);
}
