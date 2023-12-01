package com.micro.reima.service;

import com.micro.reima.model.admin.BizCouponInfo;
import com.micro.reima.model.admin.UserMobile;
import java.util.List;
import com.micro.reima.domain.BizCoupon;
import com.micro.reima.model.vo.SimpleCouponVo;

/**
 * 优惠券Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizCouponService 
{
    public List<SimpleCouponVo> appList();

    public List<BizCouponInfo> kvList();

    public int send(UserMobile userMobile);

    public int sendBatch(Long id);
    /**
     * 查询优惠券
     * 
     * @param id 优惠券主键
     * @return 优惠券
     */
    public BizCoupon selectBizCouponById(Long id);

    /**
     * 查询优惠券列表
     * 
     * @param bizCoupon 优惠券
     * @return 优惠券集合
     */
    public List<BizCoupon> selectBizCouponList(BizCoupon bizCoupon);

    /**
     * 新增优惠券
     * 
     * @param bizCoupon 优惠券
     * @return 结果
     */
    public int insertBizCoupon(BizCoupon bizCoupon);

    /**
     * 修改优惠券
     * 
     * @param bizCoupon 优惠券
     * @return 结果
     */
    public int updateBizCoupon(BizCoupon bizCoupon);

    /**
     * 批量删除优惠券
     * 
     * @param ids 需要删除的优惠券主键集合
     * @return 结果
     */
    public int deleteBizCouponByIds(Long[] ids);

    /**
     * 删除优惠券信息
     * 
     * @param id 优惠券主键
     * @return 结果
     */
    public int deleteBizCouponById(Long id);
}
