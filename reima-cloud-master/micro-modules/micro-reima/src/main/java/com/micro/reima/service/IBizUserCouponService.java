package com.micro.reima.service;

import com.micro.reima.model.admin.BizUserCouponVo;
import com.micro.reima.model.pos.CheckMemberCouponBody;
import com.micro.reima.model.pos.CheckMemberCouponVo;
import com.micro.reima.model.pos.IssueMemberCouponBody;
import com.micro.reima.model.pos.IssueMemberCouponBodyVo;
import com.micro.reima.model.pos.MemberCouponBody;
import com.micro.reima.model.pos.MemberCouponVo;
import com.micro.reima.model.vo.SimpleUserCouponVo;
import java.util.List;
import com.micro.reima.domain.BizUserCoupon;

/**
 * 用户优惠券Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizUserCouponService 
{

    public List<SimpleUserCouponVo> appList(Long userId,Integer status);

    public BizUserCoupon save(Long couponId,Long userId,String from);

    public BizUserCoupon saveOnce(Long couponId,Long userId,String from);

    List<MemberCouponVo> queryMemberCouponList(MemberCouponBody body);

    public MemberCouponVo queryMemberCoupon(String serialNum);

    CheckMemberCouponVo checkMemberCoupon(CheckMemberCouponBody body);

    IssueMemberCouponBodyVo issueMemberCoupon(IssueMemberCouponBody body);

    /**
     * 查询用户优惠券
     * 
     * @param id 用户优惠券主键
     * @return 用户优惠券
     */
    public BizUserCouponVo selectBizUserCouponById(Long id);

    /**
     * 查询用户优惠券列表
     * 
     * @param bizUserCoupon 用户优惠券
     * @return 用户优惠券集合
     */
    public List<BizUserCouponVo> selectBizUserCouponList(BizUserCouponVo bizUserCoupon);

    /**
     * 新增用户优惠券
     * 
     * @param bizUserCoupon 用户优惠券
     * @return 结果
     */
    public int insertBizUserCoupon(BizUserCoupon bizUserCoupon);

    /**
     * 修改用户优惠券
     * 
     * @param bizUserCoupon 用户优惠券
     * @return 结果
     */
    public int updateBizUserCoupon(BizUserCoupon bizUserCoupon);

    /**
     * 批量删除用户优惠券
     * 
     * @param ids 需要删除的用户优惠券主键集合
     * @return 结果
     */
    public int deleteBizUserCouponByIds(Long[] ids);

    /**
     * 删除用户优惠券信息
     * 
     * @param id 用户优惠券主键
     * @return 结果
     */
    public int deleteBizUserCouponById(Long id);

    public void checkBizUserCoupon();
}
