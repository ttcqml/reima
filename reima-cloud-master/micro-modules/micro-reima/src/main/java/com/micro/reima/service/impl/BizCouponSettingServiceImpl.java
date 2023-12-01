package com.micro.reima.service.impl;

import java.util.List;

import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.DateUtils;
import com.micro.reima.domain.BizCoupon;
import com.micro.reima.domain.BizCouponSetting;
import com.micro.reima.mapper.BizCouponMapper;
import com.micro.reima.mapper.BizCouponSettingMapper;
import com.micro.reima.service.IBizCouponSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 优惠券入会赠送配置Service业务层处理
 * 
 * @author micro
 * @date 2022-03-05
 */
@Service
public class BizCouponSettingServiceImpl implements IBizCouponSettingService
{
    @Autowired
    private BizCouponSettingMapper bizCouponSettingMapper;
    @Autowired
    private BizCouponMapper bizCouponMapper;

    /**
     * 查询优惠券入会赠送配置
     * 
     * @param id 优惠券入会赠送配置主键
     * @return 优惠券入会赠送配置
     */
    @Override
    public BizCouponSetting selectBizCouponSettingById(Long id)
    {
        return bizCouponSettingMapper.selectBizCouponSettingById(id);
    }

    /**
     * 查询优惠券入会赠送配置列表
     * 
     * @param bizCouponSetting 优惠券入会赠送配置
     * @return 优惠券入会赠送配置
     */
    @Override
    public List<BizCouponSetting> selectBizCouponSettingList(BizCouponSetting bizCouponSetting)
    {
        return bizCouponSettingMapper.selectBizCouponSettingList(bizCouponSetting);
    }

    /**
     * 新增优惠券入会赠送配置
     * 
     * @param bizCouponSetting 优惠券入会赠送配置
     * @return 结果
     */
    @Override
    public int insertBizCouponSetting(BizCouponSetting bizCouponSetting)
    {
        BizCoupon bizCoupon = bizCouponMapper.selectBizCouponById(bizCouponSetting.getCouponId());
        if(null == bizCoupon){
            throw new ServiceException("数据不存在");
        }
        bizCouponSetting.setName(bizCoupon.getName());
        bizCouponSetting.setCreateTime(DateUtils.getNowDate());
        return bizCouponSettingMapper.insertBizCouponSetting(bizCouponSetting);
    }

    /**
     * 修改优惠券入会赠送配置
     * 
     * @param bizCouponSetting 优惠券入会赠送配置
     * @return 结果
     */
    @Override
    public int updateBizCouponSetting(BizCouponSetting bizCouponSetting)
    {
        bizCouponSetting.setUpdateTime(DateUtils.getNowDate());
        return bizCouponSettingMapper.updateBizCouponSetting(bizCouponSetting);
    }

    /**
     * 批量删除优惠券入会赠送配置
     * 
     * @param ids 需要删除的优惠券入会赠送配置主键
     * @return 结果
     */
    @Override
    public int deleteBizCouponSettingByIds(Long[] ids)
    {
        return bizCouponSettingMapper.deleteBizCouponSettingByIds(ids);
    }

    /**
     * 删除优惠券入会赠送配置信息
     * 
     * @param id 优惠券入会赠送配置主键
     * @return 结果
     */
    @Override
    public int deleteBizCouponSettingById(Long id)
    {
        return bizCouponSettingMapper.deleteBizCouponSettingById(id);
    }
}
