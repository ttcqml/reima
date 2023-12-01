package com.micro.reima.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.reima.domain.BizOldOrder;
import com.micro.reima.domain.BizOrderDetail;
import com.micro.reima.domain.BizOrderRecord;
import com.micro.reima.mapper.BizOldOrderMapper;
import com.micro.reima.mapper.BizOrderDetailMapper;
import com.micro.reima.mapper.BizOrderRecordMapper;
import com.micro.reima.service.IBizOldOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 老订单Service业务层处理
 * 
 * @author micro
 * @date 2022-02-25
 */
@Service
public class BizOldOrderServiceImpl implements IBizOldOrderService
{
    @Autowired
    private BizOldOrderMapper bizOldOrderMapper;
    @Autowired
    private BizOrderRecordMapper bizOrderRecordMapper;
    @Autowired
    private BizOrderDetailMapper bizOrderDetailMapper;

//    @Scheduled(cron = "0 0/10 * * * ? ")
    public void syncToOrder() {
        List<BizOldOrder> bizOldOrders = bizOldOrderMapper.selectMatchByPhonenumber();
        if(CollectionUtils.isEmpty(bizOldOrders)){
            return;
        }
        Map<String, BizOrderRecord> orderRecordMap = new HashMap<>();
        Map<String,List<BizOrderDetail>> orderDetailMap = new HashMap<>();
        for (BizOldOrder order:bizOldOrders){
            BizOrderRecord record = orderRecordMap.get(order.getInvoiceNo());
            if(null == record){
                record = buildBizOrderRecord(order);
            }else{
                record.setAmt(record.getAmt().add(order.getAmt()));
                record.setNum(record.getNum()+order.getNum());
            }
            if(order.getNum()<0){
                record.setIsRefund("1");
            }
            orderRecordMap.put(order.getInvoiceNo(),record);
            List<BizOrderDetail> details = orderDetailMap.get(order.getInvoiceNo());
            if(CollectionUtils.isEmpty(details)){
                details = new ArrayList<>();
            }
            details.add(buildBizOrderDetail(order));
            orderDetailMap.put(order.getInvoiceNo(),details);
        }
        for (String invoiceNo:orderRecordMap.keySet()) {
            BizOrderRecord bizOrderRecord = orderRecordMap.get(invoiceNo);
            bizOrderRecordMapper.insertBizOrderRecord(bizOrderRecord);
            List<BizOrderDetail> bizOrderDetails = orderDetailMap.get(invoiceNo);
            if(!CollectionUtils.isEmpty(bizOrderDetails)){
                for (BizOrderDetail detail:bizOrderDetails){
                    detail.setOrderId(bizOrderRecord.getId());
                    bizOrderDetailMapper.insertBizOrderDetail(detail);
                }
            }
            bizOldOrderMapper.updateByInvoiceNo(invoiceNo);
        }
    }

    private BizOrderDetail buildBizOrderDetail(BizOldOrder bizOldOrder){
        BizOrderDetail bizOrderDetail = new BizOrderDetail();
        bizOrderDetail.setOrderId(null);
        bizOrderDetail.setPrdCode(bizOldOrder.getGoodCode());
        bizOrderDetail.setPrdSpec(bizOldOrder.getGoodCode());
        bizOrderDetail.setNum(bizOldOrder.getNum());
        bizOrderDetail.setDiscount(bizOldOrder.getDiscount());
        bizOrderDetail.setRealPrice(bizOldOrder.getPrice());
        bizOrderDetail.setEmployeeCode(null);
        bizOrderDetail.setDelFlag("0");
        bizOrderDetail.setCreateBy("old order");
        bizOrderDetail.setUpdateBy("old order");
        bizOrderDetail.setCreateTime(bizOldOrder.getCreateTime());
        bizOrderDetail.setUpdateTime(DateUtils.getNowDate());
        return bizOrderDetail;
    }

    private BizOrderRecord buildBizOrderRecord(BizOldOrder bizOldOrder){
        BizOrderRecord record = new BizOrderRecord();
        record.setInvoiceNo(bizOldOrder.getInvoiceNo());
        record.setAmt(bizOldOrder.getPrice());
        record.setOriginalInvoiceNo(null);
        record.setNum(bizOldOrder.getNum());
        record.setShopCode("P10004");// TODO 固定
        record.setDiscount(bizOldOrder.getDiscount());
        //record.setPayType(body.getTransaction_type_code());
        record.setPayTime(bizOldOrder.getCreateTime());
        record.setCreateTime(bizOldOrder.getCreateTime());
        record.setIsRefund("0");
        record.setDelFlag("0");
        if(record.getAmt().compareTo(BigDecimal.ZERO)==-1){
            record.setIsRefund("1");
        }
        record.setCreateBy("old order");
        record.setUpdateBy("old order");
        record.setUpdateTime(DateUtils.getNowDate());
        return record;
    }
    /**
     * 查询老订单
     * 
     * @param id 老订单主键
     * @return 老订单
     */
    @Override
    public BizOldOrder selectBizOldOrderById(Long id)
    {
        return bizOldOrderMapper.selectBizOldOrderById(id);
    }

    /**
     * 查询老订单列表
     * 
     * @param bizOldOrder 老订单
     * @return 老订单
     */
    @Override
    public List<BizOldOrder> selectBizOldOrderList(BizOldOrder bizOldOrder)
    {
        return bizOldOrderMapper.selectBizOldOrderList(bizOldOrder);
    }

    /**
     * 新增老订单
     * 
     * @param bizOldOrder 老订单
     * @return 结果
     */
    @Override
    public int insertBizOldOrder(BizOldOrder bizOldOrder)
    {
        bizOldOrder.setCreateTime(DateUtils.getNowDate());
        return bizOldOrderMapper.insertBizOldOrder(bizOldOrder);
    }

    /**
     * 修改老订单
     * 
     * @param bizOldOrder 老订单
     * @return 结果
     */
    @Override
    public int updateBizOldOrder(BizOldOrder bizOldOrder)
    {
        bizOldOrder.setUpdateTime(DateUtils.getNowDate());
        return bizOldOrderMapper.updateBizOldOrder(bizOldOrder);
    }

    /**
     * 批量删除老订单
     * 
     * @param ids 需要删除的老订单主键
     * @return 结果
     */
    @Override
    public int deleteBizOldOrderByIds(Long[] ids)
    {
        return bizOldOrderMapper.deleteBizOldOrderByIds(ids);
    }

    /**
     * 删除老订单信息
     * 
     * @param id 老订单主键
     * @return 结果
     */
    @Override
    public int deleteBizOldOrderById(Long id)
    {
        return bizOldOrderMapper.deleteBizOldOrderById(id);
    }
}
