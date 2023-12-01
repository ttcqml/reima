package com.micro.reima.mapper;

import com.micro.reima.model.admin.BizUserCouponVo;
import com.micro.reima.model.bo.BizUserCouponNoticeVo;
import com.micro.reima.model.pos.CheckMemberCouponVo;
import com.micro.reima.model.pos.MemberCouponVo;
import com.micro.reima.model.vo.SimpleUserCouponVo;
import java.util.List;
import com.micro.reima.domain.BizUserCoupon;
import org.apache.ibatis.annotations.Param;

/**
 * 用户优惠券Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizUserCouponMapper 
{
    int checkIsNew(@Param("userId") Long userId);

    public List<SimpleUserCouponVo> appList(@Param("userId") Long userId, @Param("status") Integer status);

    BizUserCoupon selectByVerificationCode(@Param("verificationCode") String verificationCode);

    List<MemberCouponVo> queryMemberCouponList(@Param("mobile") String mobile,@Param("from") String from,@Param("to") String to);

    MemberCouponVo getCoupon(@Param("verificationCode") String verificationCode);

    CheckMemberCouponVo checkMemberCoupon(@Param("mobile") String mobile,@Param("verificationCode") String verificationCode);

    BizUserCoupon selectByMobileAndVerificationCode(@Param("mobile") String mobile,@Param("verificationCode") String verificationCode);

    List<BizUserCoupon> selectBizUserCouponByUserIdAndCouponId(@Param("userId") Long userId,@Param("couponId") Long couponId);

    int countByCouponIdToday(@Param("couponId") Long couponId);

    int countByCouponIdMonth(@Param("couponId") Long couponId);

    int resetIsNew(@Param("userId") Long userId);

    List<BizUserCouponNoticeVo> selectBizUserCouponNoticeVos();
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
     * 删除用户优惠券
     * 
     * @param id 用户优惠券主键
     * @return 结果
     */
    public int deleteBizUserCouponById(Long id);

    /**
     * 批量删除用户优惠券
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserCouponByIds(Long[] ids);

    public void checkBizUserCoupon();
}
