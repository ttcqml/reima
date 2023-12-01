package com.micro.reima.mapper;

import java.util.List;
import com.micro.reima.domain.BizCoupon;
import com.micro.reima.model.admin.BizCouponInfo;
import com.micro.reima.model.vo.SimpleCouponVo;

/**
 * 优惠券Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizCouponMapper 
{

    public List<SimpleCouponVo> appList();

    public List<BizCouponInfo> kvList();
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
     * 删除优惠券
     * 
     * @param id 优惠券主键
     * @return 结果
     */
    public int deleteBizCouponById(Long id);

    /**
     * 批量删除优惠券
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizCouponByIds(Long[] ids);
}
