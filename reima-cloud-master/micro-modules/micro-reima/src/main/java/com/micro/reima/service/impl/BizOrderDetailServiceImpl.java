package com.micro.reima.service.impl;

import com.micro.common.core.utils.StringUtils;
import com.micro.reima.model.pos.TransactionDetail;
import com.micro.reima.model.pos.TransactionTender;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizOrderDetailMapper;
import com.micro.reima.domain.BizOrderDetail;
import com.micro.reima.service.IBizOrderDetailService;
import org.springframework.util.CollectionUtils;

/**
 * 订单明细Service业务层处理
 * 
 * @author micro
 * @date 2022-01-12
 */
@Service
public class BizOrderDetailServiceImpl implements IBizOrderDetailService 
{
    @Autowired
    private BizOrderDetailMapper bizOrderDetailMapper;

    @Override
    public void saveBizOrderDetail(List<TransactionDetail> details, Long orderId) {
        if(CollectionUtils.isEmpty(details)){
            return;
        }
        for (TransactionDetail detail:details){
            bizOrderDetailMapper.insertBizOrderDetail(buildBizOrderDetail(detail,orderId));
        }
    }

    private BizOrderDetail buildBizOrderDetail(TransactionDetail detail,Long orderId){
        BizOrderDetail bizOrderDetail = new BizOrderDetail();
        bizOrderDetail.setOrderId(orderId);
        bizOrderDetail.setPrdCode(detail.getProduct_code());
        bizOrderDetail.setPrdSpec(detail.getProduct_spec_code());
        bizOrderDetail.setNum(StringUtils.str2Num(detail.getQuantity()).longValue());
        bizOrderDetail.setDiscount(StringUtils.str2Num(detail.getDiscount()));
        bizOrderDetail.setRealPrice(StringUtils.str2Num(detail.getReal_price()));
        bizOrderDetail.setEmployeeCode(detail.getEmployee_code());
        bizOrderDetail.setDelFlag("0");
        bizOrderDetail.setCreateBy("api");
        bizOrderDetail.setUpdateBy("api");
        bizOrderDetail.setCreateTime(DateUtils.getNowDate());
        bizOrderDetail.setUpdateTime(DateUtils.getNowDate());
        return bizOrderDetail;
    }

    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    @Override
    public BizOrderDetail selectBizOrderDetailById(Long id)
    {
        return bizOrderDetailMapper.selectBizOrderDetailById(id);
    }

    /**
     * 查询订单明细列表
     * 
     * @param bizOrderDetail 订单明细
     * @return 订单明细
     */
    @Override
    public List<BizOrderDetail> selectBizOrderDetailList(BizOrderDetail bizOrderDetail)
    {
        return bizOrderDetailMapper.selectBizOrderDetailList(bizOrderDetail);
    }

    /**
     * 新增订单明细
     * 
     * @param bizOrderDetail 订单明细
     * @return 结果
     */
    @Override
    public int insertBizOrderDetail(BizOrderDetail bizOrderDetail)
    {
        bizOrderDetail.setCreateTime(DateUtils.getNowDate());
        return bizOrderDetailMapper.insertBizOrderDetail(bizOrderDetail);
    }

    /**
     * 修改订单明细
     * 
     * @param bizOrderDetail 订单明细
     * @return 结果
     */
    @Override
    public int updateBizOrderDetail(BizOrderDetail bizOrderDetail)
    {
        bizOrderDetail.setUpdateTime(DateUtils.getNowDate());
        return bizOrderDetailMapper.updateBizOrderDetail(bizOrderDetail);
    }

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键
     * @return 结果
     */
    @Override
    public int deleteBizOrderDetailByIds(Long[] ids)
    {
        return bizOrderDetailMapper.deleteBizOrderDetailByIds(ids);
    }

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    @Override
    public int deleteBizOrderDetailById(Long id)
    {
        return bizOrderDetailMapper.deleteBizOrderDetailById(id);
    }
}
