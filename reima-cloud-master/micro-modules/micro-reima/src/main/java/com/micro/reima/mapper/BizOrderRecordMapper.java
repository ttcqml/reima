package com.micro.reima.mapper;

import com.micro.reima.model.admin.BizOrderRecordVo;
import com.micro.reima.model.bo.RefundOrderBo;
import com.micro.reima.model.bo.TradeOrderBo;
import com.micro.reima.model.vo.UserOrderRecordVo;
import java.util.List;
import com.micro.reima.domain.BizOrderRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 订单明细Mapper接口
 * 
 * @author micro
 * @date 2022-01-05
 */
public interface BizOrderRecordMapper 
{

    public List<TradeOrderBo> syncTradeOrder();
    public List<RefundOrderBo> syncRefundOrder();
    public int updateSyncTime(Long id);

    public List<UserOrderRecordVo> selectUserOrderRecordVoByUserId(@Param("userId") Long userId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    public void refund(@Param("originalInvoiceNo") String originalInvoiceNo);

    public Long selectByInvoiceNo(@Param("invoiceNo") String invoiceNo);
    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    public BizOrderRecordVo selectBizOrderRecordById(Long id);

    /**
     * 查询订单明细列表
     * 
     * @param bizOrderRecord 订单明细
     * @return 订单明细集合
     */
    public List<BizOrderRecordVo> selectBizOrderRecordList(BizOrderRecordVo bizOrderRecord);

    /**
     * 新增订单明细
     * 
     * @param bizOrderRecord 订单明细
     * @return 结果
     */
    public int insertBizOrderRecord(BizOrderRecord bizOrderRecord);

    /**
     * 修改订单明细
     * 
     * @param bizOrderRecord 订单明细
     * @return 结果
     */
    public int updateBizOrderRecord(BizOrderRecord bizOrderRecord);

    /**
     * 删除订单明细
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteBizOrderRecordById(Long id);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizOrderRecordByIds(Long[] ids);
}
