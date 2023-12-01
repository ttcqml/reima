package com.micro.reima.service.impl;

import com.alibaba.fastjson.JSON;
import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizCoupon;
import com.micro.reima.domain.BizUserCoupon;
import com.micro.reima.domain.SysUserInfo;
import com.micro.reima.mapper.BizCouponMapper;
import com.micro.reima.mapper.BizUserCouponMapper;
import com.micro.reima.mapper.BizUserInfoMapper;
import com.micro.reima.model.admin.BizUserCouponVo;
import com.micro.reima.model.pos.*;
import com.micro.reima.model.vo.SimpleUserCouponVo;
import com.micro.reima.service.IBizMessageService;
import com.micro.reima.service.IBizUserCouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户优惠券Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
@Slf4j
public class BizUserCouponServiceImpl implements IBizUserCouponService 
{

    @Autowired
    private BizCouponMapper bizCouponMapper;
    @Autowired
    private BizUserCouponMapper bizUserCouponMapper;
    @Autowired
    private IBizMessageService bizMessageService;
    @Autowired
    private BizUserInfoMapper bizUserInfoMapper;

    @Override
    public List<SimpleUserCouponVo> appList(Long userId, Integer status) {
        bizUserCouponMapper.resetIsNew(userId);
        return bizUserCouponMapper.appList(userId,status);
    }

    @Override
    @Transactional
    public BizUserCoupon saveOnce(Long couponId, Long userId, String from) {
        BizCoupon bizCoupon = bizCouponMapper.selectBizCouponById(couponId);
        if(null == bizCoupon){
            throw new ServiceException("优惠券信息不存在");
        }
        // 判断是否已存在
        List<BizUserCoupon> bizUserCoupons = bizUserCouponMapper.selectBizUserCouponByUserIdAndCouponId(userId,couponId);
        if(!CollectionUtils.isEmpty(bizUserCoupons)){
            return bizUserCoupons.get(0);
            //throw new ServiceException("优惠券已存在,不可重复赠送");
        }
        BizUserCoupon bizUserCoupon = buildBizUserCoupon(couponId,userId);
        bizUserCoupon.setVerificationCode(genVerificationCode());
        if(0==bizCoupon.getTimeType()){
            bizUserCoupon.setStartTime(DateUtils.getNowDate());
            bizUserCoupon.setEndTime(DateUtils.addDays(bizUserCoupon.getStartTime(),Integer.valueOf(bizCoupon.getDays()+"")));
        }else{
            bizUserCoupon.setStartTime(bizCoupon.getStartTime());
            bizUserCoupon.setEndTime(bizCoupon.getEndTime());
        }
        bizUserCoupon.setCreateBy(from);
        try {
            bizUserCoupon.setUpdateBy(SecurityUtils.getUsername());
        }catch (Exception e){
            bizUserCoupon.setUpdateBy("system");
        }
        // 消息通知
        SysUserInfo sysUserInfo = bizUserInfoMapper.selectSysUserByUserId(userId);
        if(null!=sysUserInfo&& StringUtils.isNotEmpty(sysUserInfo.getWxOpenid())){
            String tips = "恭喜您已获得（"+bizCoupon.getName()+"）";
            if(bizCoupon.getId()==8){
                tips = "祝宝贝生日快乐，7折优惠券已放入卡券包";
            }
            bizMessageService.sendCouponGetNotice(sysUserInfo.getWxOpenid(),bizCoupon.getName(),DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",bizUserCoupon.getEndTime()),tips);
        }
        bizUserCouponMapper.insertBizUserCoupon(bizUserCoupon);
        return bizUserCoupon;
    }

    @Override
    @Transactional
    public BizUserCoupon save(Long couponId,Long userId,String from){
        BizCoupon bizCoupon = bizCouponMapper.selectBizCouponById(couponId);
        if(null == bizCoupon){
            throw new ServiceException("优惠券信息不存在");
        }
        BizUserCoupon bizUserCoupon = buildBizUserCoupon(couponId,userId);
        bizUserCoupon.setVerificationCode(genVerificationCode());
        if(0==bizCoupon.getTimeType()){
            bizUserCoupon.setStartTime(DateUtils.getNowDate());
            bizUserCoupon.setEndTime(DateUtils.addDays(bizUserCoupon.getStartTime(),Integer.valueOf(bizCoupon.getDays()+"")));
        }else{
            bizUserCoupon.setStartTime(bizCoupon.getStartTime());
            bizUserCoupon.setEndTime(bizCoupon.getEndTime());
        }
        bizUserCoupon.setCreateBy(from);
        try {
            bizUserCoupon.setUpdateBy(SecurityUtils.getUsername());
        }catch (Exception e){
            bizUserCoupon.setUpdateBy("system");
        }
        // 消息通知
        SysUserInfo sysUserInfo = bizUserInfoMapper.selectSysUserByUserId(userId);
        if(null!=sysUserInfo&& StringUtils.isNotEmpty(sysUserInfo.getWxOpenid())){
            String tips = "恭喜您已获得（"+bizCoupon.getName()+"）";
            if(bizCoupon.getId()==8){
                tips = "祝宝贝生日快乐，7折优惠券已放入卡券包";
            }
            bizMessageService.sendCouponGetNotice(sysUserInfo.getWxOpenid(),bizCoupon.getName(),DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",bizUserCoupon.getEndTime()),tips);
        }
        bizUserCouponMapper.insertBizUserCoupon(bizUserCoupon);
        return bizUserCoupon;
    }

    private String genVerificationCode(){
        String code = RandomStringUtils.randomNumeric(13);
        if(null!=bizUserCouponMapper.selectByVerificationCode(code)){
            return genVerificationCode();
        }
        return code;
    }

    private BizUserCoupon buildBizUserCoupon(Long couponId,Long userId){
        BizUserCoupon bizUserCoupon = new BizUserCoupon();
        bizUserCoupon.setUserId(userId);
        bizUserCoupon.setCouponId(couponId);
        bizUserCoupon.setStatus(0L);
        bizUserCoupon.setDelFlag("0");
        bizUserCoupon.setCreateBy("system");
        bizUserCoupon.setCreateTime(DateUtils.getNowDate());
        bizUserCoupon.setUpdateBy("system");
        bizUserCoupon.setUpdateTime(DateUtils.getNowDate());
        return bizUserCoupon;
    }

    @Override
    public List<MemberCouponVo> queryMemberCouponList(MemberCouponBody body) {
        String from = null!=body.getCreate_date_from()?DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,body.getCreate_date_from()):null;
        String to = null!=body.getCreate_date_to()?DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,body.getCreate_date_to()):null;
        String mobile = body.getMember_code();
        log.info(">>>>>>queryMemberCouponList：from:{},to:{}",from,to);
        List<MemberCouponVo> vos = bizUserCouponMapper.queryMemberCouponList(mobile,from,to);
        for (MemberCouponVo vo:vos){
            vo.setCoupon_info(new CouponInfoVo(vo.getMin()));
        }
        return vos;
    }

    @Override
    public MemberCouponVo queryMemberCoupon(String serialNum) {
        MemberCouponVo vo = bizUserCouponMapper.getCoupon(serialNum);
        vo.setCoupon_info(new CouponInfoVo(vo.getMin()));
        return vo;
    }

    @Override
    public CheckMemberCouponVo checkMemberCoupon(CheckMemberCouponBody body) {
        CheckMemberCouponVo vo = bizUserCouponMapper.checkMemberCoupon(body.getMember_code(),body.getCoupon_serial_no());
        if(null == vo){
            vo = new CheckMemberCouponVo("false");
        }else{
            vo.setExist("true");
        }
        return vo;
    }

    @Override
    @Transactional
    public IssueMemberCouponBodyVo issueMemberCoupon(IssueMemberCouponBody body) {
        IssueMemberCouponBodyVo vo = new IssueMemberCouponBodyVo(false);
        BizUserCoupon bizUserCoupon = bizUserCouponMapper.selectByMobileAndVerificationCode(body.getMember_code(),body.getCoupon_serial_no());
        if(null == bizUserCoupon){
            log.info("核销优惠券信息不存在或者不可用 {}", JSON.toJSONString(body));
            vo.setSuccess(false);
            return vo;
        }
        if(0!=bizUserCoupon.getStatus()){
            log.info("无法核销 {}", JSON.toJSONString(body));
            vo.setSuccess(false);
            return vo;
        }
        bizUserCoupon.setStatus(1L);
        bizUserCoupon.setUsedTime(body.getIssue_time());
        bizUserCoupon.setUpdateTime(DateUtils.getNowDate());
        bizUserCouponMapper.updateBizUserCoupon(bizUserCoupon);
        // 消息通知
        SysUserInfo sysUserInfo = bizUserInfoMapper.selectSysUserByUserId(bizUserCoupon.getUserId());
        if(null!=sysUserInfo&& StringUtils.isNotEmpty(sysUserInfo.getWxOpenid())){
            String tips = "优惠券使用成功。";
            BizCoupon bizCoupon = bizCouponMapper.selectBizCouponById(bizUserCoupon.getCouponId());
            bizMessageService.sendCouponUseNotice(sysUserInfo.getWxOpenid(),bizCoupon.getName(),DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()),tips);
        }
        vo.setSuccess(true);
        return vo;
    }

    /**
     * 查询用户优惠券
     * 
     * @param id 用户优惠券主键
     * @return 用户优惠券
     */
    @Override
    public BizUserCouponVo selectBizUserCouponById(Long id)
    {
        return bizUserCouponMapper.selectBizUserCouponById(id);
    }

    /**
     * 查询用户优惠券列表
     * 
     * @param bizUserCoupon 用户优惠券
     * @return 用户优惠券
     */
    @Override
    public List<BizUserCouponVo> selectBizUserCouponList(BizUserCouponVo bizUserCoupon)
    {
        return bizUserCouponMapper.selectBizUserCouponList(bizUserCoupon);
    }

    /**
     * 新增用户优惠券
     * 
     * @param bizUserCoupon 用户优惠券
     * @return 结果
     */
    @Override
    public int insertBizUserCoupon(BizUserCoupon bizUserCoupon)
    {
        bizUserCoupon.setCreateTime(DateUtils.getNowDate());
        return bizUserCouponMapper.insertBizUserCoupon(bizUserCoupon);
    }

    /**
     * 修改用户优惠券
     * 
     * @param bizUserCoupon 用户优惠券
     * @return 结果
     */
    @Override
    public int updateBizUserCoupon(BizUserCoupon bizUserCoupon)
    {
        bizUserCoupon.setUpdateTime(DateUtils.getNowDate());
        return bizUserCouponMapper.updateBizUserCoupon(bizUserCoupon);
    }

    /**
     * 批量删除用户优惠券
     * 
     * @param ids 需要删除的用户优惠券主键
     * @return 结果
     */
    @Override
    public int deleteBizUserCouponByIds(Long[] ids)
    {
        return bizUserCouponMapper.deleteBizUserCouponByIds(ids);
    }

    /**
     * 删除用户优惠券信息
     * 
     * @param id 用户优惠券主键
     * @return 结果
     */
    @Override
    public int deleteBizUserCouponById(Long id)
    {
        return bizUserCouponMapper.deleteBizUserCouponById(id);
    }

    @Override
    public void checkBizUserCoupon() {
        bizUserCouponMapper.checkBizUserCoupon();
    }
}
