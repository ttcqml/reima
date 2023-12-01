package com.micro.reima.mapper;

import com.micro.reima.model.bo.TradeOrderChildBo;
import com.micro.reima.model.vo.OrderPrdVo;
import java.util.List;
import com.micro.reima.domain.BizOrderDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 订单明细Mapper接口
 * 
 * @author micro
 * @date 2022-01-12
 */
public interface BizOrderDetailMapper 
{
    public List<TradeOrderChildBo> syncTradeOrderChild(@Param("orderId") Long orderId);

    public Integer updateSyncTime(@Param("orderId") Long orderId);

    public List<OrderPrdVo> selectByOrderId(@Param("orderId") Long orderId);
    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    public BizOrderDetail selectBizOrderDetailById(Long id);

    /**
     * 查询订单明细列表
     * 
     * @param bizOrderDetail 订单明细
     * @return 订单明细集合
     */
    public List<BizOrderDetail> selectBizOrderDetailList(BizOrderDetail bizOrderDetail);

    /**
     * 新增订单明细
     * 
     * @param bizOrderDetail 订单明细
     * @return 结果
     */
    public int insertBizOrderDetail(BizOrderDetail bizOrderDetail);

    /**
     * 修改订单明细
     * 
     * @param bizOrderDetail 订单明细
     * @return 结果
     */
    public int updateBizOrderDetail(BizOrderDetail bizOrderDetail);

    /**
     * 删除订单明细
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteBizOrderDetailById(Long id);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizOrderDetailByIds(Long[] ids);
}
