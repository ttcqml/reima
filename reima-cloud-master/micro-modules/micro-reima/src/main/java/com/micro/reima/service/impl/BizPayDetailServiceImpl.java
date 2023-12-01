package com.micro.reima.service.impl;

import com.micro.common.core.utils.StringUtils;
import com.micro.reima.model.pos.TransactionTender;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizPayDetailMapper;
import com.micro.reima.domain.BizPayDetail;
import com.micro.reima.service.IBizPayDetailService;
import org.springframework.util.CollectionUtils;

/**
 * 支付明细Service业务层处理
 * 
 * @author micro
 * @date 2022-01-12
 */
@Service
public class BizPayDetailServiceImpl implements IBizPayDetailService 
{
    @Autowired
    private BizPayDetailMapper bizPayDetailMapper;

    @Override
    public void saveBizPayDetail(List<TransactionTender> details, Long orderId) {
        if(CollectionUtils.isEmpty(details)){
            return;
        }
        for (TransactionTender detail:details){
            bizPayDetailMapper.insertBizPayDetail(buildBizPayDetail(detail,orderId));
        }
    }

    private BizPayDetail buildBizPayDetail(TransactionTender detail,Long orderId){
        BizPayDetail bizPayDetail = new BizPayDetail();
        bizPayDetail.setOrderId(orderId);
        bizPayDetail.setAmt(StringUtils.str2Num(detail.getReal_amount()));
        bizPayDetail.setPayType(detail.getTender_type_code());
        bizPayDetail.setDiscount(StringUtils.str2Num(detail.getDiscount_point()));
        bizPayDetail.setSourceNo(detail.getSource_no());
        bizPayDetail.setVerificationCode(detail.getCoupon_serial_no());
        bizPayDetail.setDelFlag("0");
        bizPayDetail.setCreateBy("api");
        bizPayDetail.setUpdateBy("api");
        bizPayDetail.setCreateTime(DateUtils.getNowDate());
        bizPayDetail.setUpdateTime(DateUtils.getNowDate());
        return bizPayDetail;
    }

    /**
     * 查询支付明细
     * 
     * @param id 支付明细主键
     * @return 支付明细
     */
    @Override
    public BizPayDetail selectBizPayDetailById(Long id)
    {
        return bizPayDetailMapper.selectBizPayDetailById(id);
    }

    /**
     * 查询支付明细列表
     * 
     * @param bizPayDetail 支付明细
     * @return 支付明细
     */
    @Override
    public List<BizPayDetail> selectBizPayDetailList(BizPayDetail bizPayDetail)
    {
        return bizPayDetailMapper.selectBizPayDetailList(bizPayDetail);
    }

    /**
     * 新增支付明细
     * 
     * @param bizPayDetail 支付明细
     * @return 结果
     */
    @Override
    public int insertBizPayDetail(BizPayDetail bizPayDetail)
    {
        bizPayDetail.setCreateTime(DateUtils.getNowDate());
        return bizPayDetailMapper.insertBizPayDetail(bizPayDetail);
    }

    /**
     * 修改支付明细
     * 
     * @param bizPayDetail 支付明细
     * @return 结果
     */
    @Override
    public int updateBizPayDetail(BizPayDetail bizPayDetail)
    {
        bizPayDetail.setUpdateTime(DateUtils.getNowDate());
        return bizPayDetailMapper.updateBizPayDetail(bizPayDetail);
    }

    /**
     * 批量删除支付明细
     * 
     * @param ids 需要删除的支付明细主键
     * @return 结果
     */
    @Override
    public int deleteBizPayDetailByIds(Long[] ids)
    {
        return bizPayDetailMapper.deleteBizPayDetailByIds(ids);
    }

    /**
     * 删除支付明细信息
     * 
     * @param id 支付明细主键
     * @return 结果
     */
    @Override
    public int deleteBizPayDetailById(Long id)
    {
        return bizPayDetailMapper.deleteBizPayDetailById(id);
    }
}
