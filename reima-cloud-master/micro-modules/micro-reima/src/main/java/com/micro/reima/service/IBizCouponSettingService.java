package com.micro.reima.service;

import com.micro.reima.domain.BizCouponSetting;

import java.util.List;

/**
 * 优惠券入会赠送配置Service接口
 * 
 * @author micro
 * @date 2022-03-05
 */
public interface IBizCouponSettingService 
{
    /**
     * 查询优惠券入会赠送配置
     * 
     * @param id 优惠券入会赠送配置主键
     * @return 优惠券入会赠送配置
     */
    public BizCouponSetting selectBizCouponSettingById(Long id);

    /**
     * 查询优惠券入会赠送配置列表
     * 
     * @param bizCouponSetting 优惠券入会赠送配置
     * @return 优惠券入会赠送配置集合
     */
    public List<BizCouponSetting> selectBizCouponSettingList(BizCouponSetting bizCouponSetting);

    /**
     * 新增优惠券入会赠送配置
     * 
     * @param bizCouponSetting 优惠券入会赠送配置
     * @return 结果
     */
    public int insertBizCouponSetting(BizCouponSetting bizCouponSetting);

    /**
     * 修改优惠券入会赠送配置
     * 
     * @param bizCouponSetting 优惠券入会赠送配置
     * @return 结果
     */
    public int updateBizCouponSetting(BizCouponSetting bizCouponSetting);

    /**
     * 批量删除优惠券入会赠送配置
     * 
     * @param ids 需要删除的优惠券入会赠送配置主键集合
     * @return 结果
     */
    public int deleteBizCouponSettingByIds(Long[] ids);

    /**
     * 删除优惠券入会赠送配置信息
     * 
     * @param id 优惠券入会赠送配置主键
     * @return 结果
     */
    public int deleteBizCouponSettingById(Long id);
}
