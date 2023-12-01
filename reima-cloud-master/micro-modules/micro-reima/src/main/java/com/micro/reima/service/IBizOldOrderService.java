package com.micro.reima.service;

import com.micro.reima.domain.BizOldOrder;

import java.util.List;

/**
 * 老订单Service接口
 * 
 * @author micro
 * @date 2022-02-25
 */
public interface IBizOldOrderService 
{
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
     * 批量删除老订单
     * 
     * @param ids 需要删除的老订单主键集合
     * @return 结果
     */
    public int deleteBizOldOrderByIds(Long[] ids);

    /**
     * 删除老订单信息
     * 
     * @param id 老订单主键
     * @return 结果
     */
    public int deleteBizOldOrderById(Long id);
}
