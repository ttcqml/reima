package com.micro.reima.service.impl;

import com.micro.common.core.exception.ServiceException;
import com.micro.reima.domain.BizUserCoupon;
import com.micro.reima.mapper.BizUserInfoMapper;
import com.micro.reima.model.admin.BizCouponInfo;
import com.micro.reima.model.admin.UserMobile;
import com.micro.reima.service.IBizUserCouponService;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import com.micro.reima.model.vo.SimpleCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizCouponMapper;
import com.micro.reima.domain.BizCoupon;
import com.micro.reima.service.IBizCouponService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 优惠券Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizCouponServiceImpl implements IBizCouponService 
{
    @Autowired
    private BizCouponMapper bizCouponMapper;
    @Autowired
    private BizUserInfoMapper bizUserInfoMapper;
    @Autowired
    private IBizUserCouponService bizUserCouponService;

    @Override
    public List<SimpleCouponVo> appList() {
        return bizCouponMapper.appList();
    }

    @Override
    public List<BizCouponInfo> kvList() {
        return bizCouponMapper.kvList();
    }

    @Override
    @Transactional
    public int sendBatch(Long id) {
        List<Long> userIds = bizUserInfoMapper.selectAllUserIdWithMobile();
        if(CollectionUtils.isEmpty(userIds)){
            return 0;
        }
        for (Long userId:userIds){
            try {
                bizUserCouponService.saveOnce(Long.valueOf(id),userId,"sendBatch");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return 1;
    }

    @Override
    @Transactional
    public int send(UserMobile userMobile) {
        BizCoupon bizCoupon = bizCouponMapper.selectBizCouponById(userMobile.getCouponId());
//        if(null == bizCoupon || !"0".equals(bizCoupon.getDelFlag()) || 0!= bizCoupon.getStatus()){
        if(null == bizCoupon || !"0".equals(bizCoupon.getDelFlag())){
            throw new ServiceException("优惠券不可用,请刷新页面");
        }
        if(null==bizCoupon.getTotal()||bizCoupon.getTotal()<=0){
            throw new ServiceException("很抱歉,优惠券已发完");
        }
        Long userId = bizUserInfoMapper.selectUserIdByMobile(userMobile.getMobile());
        if(null==userId||0==userId){
            throw new ServiceException("用户信息不存在");
        }
        bizUserCouponService.save(userMobile.getCouponId(),userId,"system");
        bizCoupon.setTotal(bizCoupon.getTotal()-1);
        return bizCouponMapper.updateBizCoupon(bizCoupon);
    }

    /**
     * 查询优惠券
     * 
     * @param id 优惠券主键
     * @return 优惠券
     */
    @Override
    public BizCoupon selectBizCouponById(Long id)
    {
        return bizCouponMapper.selectBizCouponById(id);
    }

    /**
     * 查询优惠券列表
     * 
     * @param bizCoupon 优惠券
     * @return 优惠券
     */
    @Override
    public List<BizCoupon> selectBizCouponList(BizCoupon bizCoupon)
    {
        return bizCouponMapper.selectBizCouponList(bizCoupon);
    }

    /**
     * 新增优惠券
     * 
     * @param bizCoupon 优惠券
     * @return 结果
     */
    @Override
    public int insertBizCoupon(BizCoupon bizCoupon)
    {
        bizCoupon.setCreateTime(DateUtils.getNowDate());
        return bizCouponMapper.insertBizCoupon(bizCoupon);
    }

    /**
     * 修改优惠券
     * 
     * @param bizCoupon 优惠券
     * @return 结果
     */
    @Override
    public int updateBizCoupon(BizCoupon bizCoupon)
    {
        bizCoupon.setUpdateTime(DateUtils.getNowDate());
        return bizCouponMapper.updateBizCoupon(bizCoupon);
    }

    /**
     * 批量删除优惠券
     * 
     * @param ids 需要删除的优惠券主键
     * @return 结果
     */
    @Override
    public int deleteBizCouponByIds(Long[] ids)
    {
        return bizCouponMapper.deleteBizCouponByIds(ids);
    }

    /**
     * 删除优惠券信息
     * 
     * @param id 优惠券主键
     * @return 结果
     */
    @Override
    public int deleteBizCouponById(Long id)
    {
        return bizCouponMapper.deleteBizCouponById(id);
    }
}
