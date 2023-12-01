package com.micro.reima.service.impl;

import com.micro.common.core.enums.AccScene;
import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.IdUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.reima.domain.BizCoupon;
import com.micro.reima.domain.BizProduct;
import com.micro.reima.domain.BizUserAcc;
import com.micro.reima.domain.BizUserAddress;
import com.micro.reima.domain.BizUserCoupon;
import com.micro.reima.mapper.BizCouponMapper;
import com.micro.reima.mapper.BizProductMapper;
import com.micro.reima.mapper.BizRegionMapper;
import com.micro.reima.mapper.BizUserAccMapper;
import com.micro.reima.model.admin.BizExchangeRecordVo;
import com.micro.reima.model.bo.ExchangeBody;
import com.micro.reima.model.vo.ExCouponVo;
import com.micro.reima.model.vo.ExProductVo;
import com.micro.reima.service.IBizUserAccService;
import com.micro.reima.service.IBizUserAddressService;
import com.micro.reima.service.IBizUserCouponService;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizExchangeRecordMapper;
import com.micro.reima.domain.BizExchangeRecord;
import com.micro.reima.service.IBizExchangeRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 兑换历史Service业务层处理
 * 
 * @author micro
 * @date 2021-12-25
 */
@Service
public class BizExchangeRecordServiceImpl implements IBizExchangeRecordService 
{

    @Autowired
    private IBizUserAccService bizUserAccService;
    @Autowired
    private IBizUserCouponService bizUserCouponService;
    @Autowired
    private IBizUserAddressService bizUserAddressService;
    @Autowired
    private BizRegionMapper bizRegionMapper;
    @Autowired
    private BizCouponMapper bizCouponMapper;
    @Autowired
    private BizProductMapper bizProductMapper;
    @Autowired
    private BizExchangeRecordMapper bizExchangeRecordMapper;

    @Override
    public List<ExProductVo> exProductList(Long userId) {
        return bizExchangeRecordMapper.exProductList(userId);
    }

    @Override
    public List<ExCouponVo> exCouponList(Long userId) {
        return bizExchangeRecordMapper.exCouponList(userId);
    }

    @Override
    public int updateExpressInfo(String orderSn, String express, String expressNum) {
        return bizExchangeRecordMapper.updateExpressInfo(orderSn, express, expressNum);
    }

    @Override
    @Transactional
    public void exchange(ExchangeBody body, Long userId) {
        BizExchangeRecord bizExchangeRecord = buildBizExchangeRecord(body.getExtype(),body.getAmt(),userId);
        if ("0".equals(body.getExtype())){// 兑换商品
            BizProduct bizProduct = bizProductMapper.selectBizProductById(body.getExid());
            if(null == bizProduct || !"0".equals(bizProduct.getDelFlag()) || "0".equals(bizProduct.getIsOnSale())){
                throw new ServiceException("礼品已下架");
            }
            if(body.getAmt().compareTo(bizProduct.getPrice())!=0){
                throw new ServiceException("兑换积分数量有误");
            }
            if(null==bizProduct.getTotal()||bizProduct.getTotal()<=0){
                throw new ServiceException("很抱歉,礼品库存不足");
            }
            bizExchangeRecord.setStoreName(body.getStoreName());
            bizExchangeRecord.setStoreAddr(body.getStoreAddr());
            bizExchangeRecord.setWay(bizProduct.getWay());
            // 领取方式 1快递 2自提
            if("2".equals(bizProduct.getWay())){
                bizExchangeRecord.setState("待领取");
            }
            bizExchangeRecord.setExid(body.getExid());
            bizExchangeRecord.setName(body.getName());
            bizExchangeRecord.setTel(body.getTel());
            String region = bizRegionMapper.selectRegionByIds(new Long[]{body.getProvince(),body.getCity(),body.getArea()});
            if(!StringUtils.isEmpty(region)){
                region = region.replace(","," ") + " ";
            }
            bizExchangeRecord.setAddress(region+body.getAddress());
            // 保存用户地址信息
            BizUserAddress address = new BizUserAddress();
            address.setUserId(userId);
            address.setName(body.getName());
            address.setTel(body.getTel());
            address.setProvince(body.getProvince());
            address.setCity(body.getCity());
            address.setArea(body.getArea());
            address.setAddress(body.getAddress());
            bizProduct.setTotal(bizProduct.getTotal()-1);
            bizProductMapper.updateBizProduct(bizProduct);
            bizUserAddressService.save(address);
        }else{// 兑换优惠券
            BizCoupon bizCoupon = bizCouponMapper.selectBizCouponById(body.getExid());
            if(null == bizCoupon || !"0".equals(bizCoupon.getDelFlag()) || 0!= bizCoupon.getStatus()){
                throw new ServiceException("优惠券不可用,请刷新页面");
            }
            if(body.getAmt().compareTo(bizCoupon.getExPoints())!=0){
                throw new ServiceException("兑换积分数量有误");
            }
            if(null==bizCoupon.getTotal()||bizCoupon.getTotal()<=0){
                throw new ServiceException("很抱歉,优惠券已发完");
            }
            BizUserCoupon bizUserCoupon = bizUserCouponService.save(body.getExid(),userId,"exchange");
            bizCoupon.setTotal(bizCoupon.getTotal()-1);
            bizCouponMapper.updateBizCoupon(bizCoupon);
            bizExchangeRecord.setExid(bizUserCoupon.getId());
        }
        bizUserAccService.subUserAccBalance(userId,body.getAmt(), AccScene.积分兑换.name());
        bizExchangeRecordMapper.insertBizExchangeRecord(bizExchangeRecord);
    }

    private BizExchangeRecord buildBizExchangeRecord(String extype,BigDecimal amt, Long userId){
        BizExchangeRecord exchangeRecord = new BizExchangeRecord();
        exchangeRecord.setAmt(amt);
        exchangeRecord.setExtype(extype);
        exchangeRecord.setUserId(userId);
        exchangeRecord.setOrderSn(IdUtils.timeId());
        exchangeRecord.setState("待发货");
        exchangeRecord.setDelFlag("0");
        exchangeRecord.setCreateBy("system");
        exchangeRecord.setCreateTime(DateUtils.getNowDate());
        exchangeRecord.setUpdateBy("system");
        exchangeRecord.setUpdateTime(DateUtils.getNowDate());
        return exchangeRecord;
    }

    /**
     * 查询兑换历史
     * 
     * @param id 兑换历史主键
     * @return 兑换历史
     */
    @Override
    public BizExchangeRecordVo selectBizExchangeRecordById(Long id)
    {
        return bizExchangeRecordMapper.selectBizExchangeRecordById(id);
    }

    /**
     * 查询兑换历史列表
     * 
     * @param bizExchangeRecord 兑换历史
     * @return 兑换历史
     */
    @Override
    public List<BizExchangeRecordVo> selectBizExchangeRecordList(BizExchangeRecordVo bizExchangeRecord)
    {
        return bizExchangeRecordMapper.selectBizExchangeRecordList(bizExchangeRecord);
    }

    /**
     * 新增兑换历史
     * 
     * @param bizExchangeRecord 兑换历史
     * @return 结果
     */
    @Override
    public int insertBizExchangeRecord(BizExchangeRecord bizExchangeRecord)
    {
        bizExchangeRecord.setCreateTime(DateUtils.getNowDate());
        return bizExchangeRecordMapper.insertBizExchangeRecord(bizExchangeRecord);
    }

    /**
     * 修改兑换历史
     * 
     * @param bizExchangeRecord 兑换历史
     * @return 结果
     */
    @Override
    public int updateBizExchangeRecord(BizExchangeRecord bizExchangeRecord)
    {
        bizExchangeRecord.setUpdateTime(DateUtils.getNowDate());
        return bizExchangeRecordMapper.updateBizExchangeRecord(bizExchangeRecord);
    }

    /**
     * 批量删除兑换历史
     * 
     * @param ids 需要删除的兑换历史主键
     * @return 结果
     */
    @Override
    public int deleteBizExchangeRecordByIds(Long[] ids)
    {
        return bizExchangeRecordMapper.deleteBizExchangeRecordByIds(ids);
    }

    /**
     * 删除兑换历史信息
     * 
     * @param id 兑换历史主键
     * @return 结果
     */
    @Override
    public int deleteBizExchangeRecordById(Long id)
    {
        return bizExchangeRecordMapper.deleteBizExchangeRecordById(id);
    }
}
