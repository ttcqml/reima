package com.micro.reima.mapper;

import com.micro.reima.domain.BizOldOrder;
import com.micro.reima.model.bo.RefundOrderBo;
import com.micro.reima.model.bo.TradeOrderBo;
import com.micro.reima.model.bo.TradeOrderChildBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 老订单Mapper接口
 * 
 * @author micro
 * @date 2022-02-25
 */
public interface BizOldOrderMapper 
{

    public List<RefundOrderBo> syncRefundOrder();

    public List<TradeOrderBo> syncTradeOrder();
    public List<TradeOrderChildBo> syncTradeOrderChild(@Param("invoiceNo") String invoiceNo);
    public int updateSyncTime(@Param("invoiceNo") String invoiceNo);

    public List<BizOldOrder> selectMatchByPhonenumber();
    public int updateByInvoiceNo(@Param("invoiceNo") String invoiceNo);

    /**
     * 查询老订单
     * 
     * @param id 老订单主键
     * @return 老订单
     */
    public BizOldOrder selectBizOldOrderById(Long id);

    /**
     * 查询老订单列表
     * 
     * @param bizOldOrder 老订单
     * @return 老订单集合
     */
    public List<BizOldOrder> selectBizOldOrderList(BizOldOrder bizOldOrder);

    /**
     * 新增老订单
     * 
     * @param bizOldOrder 老订单
     * @return 结果
     */
    public int insertBizOldOrder(BizOldOrder bizOldOrder);

    /**
     * 修改老订单
     * 
     * @param bizOldOrder 老订单
     * @return 结果
     */
    public int updateBizOldOrder(BizOldOrder bizOldOrder);

    /**
     * 删除老订单
     * 
     * @param id 老订单主键
     * @return 结果
     */
    public int deleteBizOldOrderById(Long id);

    /**
     * 批量删除老订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizOldOrderByIds(Long[] ids);
}
