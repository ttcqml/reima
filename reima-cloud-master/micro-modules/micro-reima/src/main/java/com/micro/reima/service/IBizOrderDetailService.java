package com.micro.reima.service;

import com.micro.reima.model.pos.TransactionDetail;
import java.util.List;
import com.micro.reima.domain.BizOrderDetail;

/**
 * 订单明细Service接口
 * 
 * @author micro
 * @date 2022-01-12
 */
public interface IBizOrderDetailService 
{
    public void saveBizOrderDetail(List<TransactionDetail> details,Long orderId);
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
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键集合
     * @return 结果
     */
    public int deleteBizOrderDetailByIds(Long[] ids);

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteBizOrderDetailById(Long id);
}
