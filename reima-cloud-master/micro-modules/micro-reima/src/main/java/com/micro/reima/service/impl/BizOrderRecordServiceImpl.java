package com.micro.reima.service.impl;

import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.StringUtils;
import com.micro.reima.mapper.BizOrderDetailMapper;
import com.micro.reima.mapper.BizUserInfoMapper;
import com.micro.reima.model.admin.BizOrderRecordVo;
import com.micro.reima.model.pos.CreateTransactionBody;
import com.micro.reima.model.pos.CreateTransactionVo;
import com.micro.reima.model.pos.VoidTransactionBody;
import com.micro.reima.model.pos.VoidTransactionVo;
import com.micro.reima.model.vo.UserOrderRecordVo;
import com.micro.reima.service.IBizOrderDetailService;
import com.micro.reima.service.IBizPayDetailService;

import java.math.BigDecimal;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizOrderRecordMapper;
import com.micro.reima.domain.BizOrderRecord;
import com.micro.reima.service.IBizOrderRecordService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 订单明细Service业务层处理
 * 
 * @author micro
 * @date 2022-01-05
 */
@Service
public class BizOrderRecordServiceImpl implements IBizOrderRecordService 
{

    @Autowired
    private BizUserInfoMapper bizUserInfoMapper;
    @Autowired
    private BizOrderRecordMapper bizOrderRecordMapper;
    @Autowired
    private BizOrderDetailMapper bizOrderDetailMapper;
    @Autowired
    private IBizOrderDetailService bizOrderDetailService;
    @Autowired
    private IBizPayDetailService bizPayDetailService;

    @Override
    public List<UserOrderRecordVo> selectUserOrderRecordVoByUserId(Long userId, String startTime, String endTime) {
        List<UserOrderRecordVo> vos = bizOrderRecordMapper.selectUserOrderRecordVoByUserId(userId,startTime,endTime);
        if(CollectionUtils.isEmpty(vos)){
            return vos;
        }
        for (UserOrderRecordVo vo:vos){
            vo.setPrdVos(bizOrderDetailMapper.selectByOrderId(vo.getId()));
        }
        return vos;
    }

    @Override
    @Transactional
    public CreateTransactionVo createTransaction(CreateTransactionBody body) {
        CreateTransactionVo vo = new CreateTransactionVo(body.getInvoice_no());
        BizOrderRecord bizOrderRecord = buildBizOrderRecord(body);
        if(StringUtils.isEmpty(body.getMember_code())){
            throw new ServiceException("手机号不能为空");
        }
        Long userId = bizUserInfoMapper.selectUserIdByMobile(body.getMember_code());
        if(null == userId){
            throw new ServiceException("用户信息不存在");
        }
        if(null!=bizOrderRecordMapper.selectByInvoiceNo(bizOrderRecord.getInvoiceNo())){
            throw new ServiceException("订单已存在");
        }
        if("1".equals(bizOrderRecord.getIsRefund())&&null==bizOrderRecordMapper.selectByInvoiceNo(bizOrderRecord.getOriginalInvoiceNo())){
            throw new ServiceException("原订单信息不存在");
        }
        bizOrderRecord.setUserId(userId);
        bizOrderRecordMapper.insertBizOrderRecord(bizOrderRecord);
        bizOrderDetailService.saveBizOrderDetail(body.getTransaction_details(),bizOrderRecord.getId());
        bizPayDetailService.saveBizPayDetail(body.getTransaction_tenders(),bizOrderRecord.getId());
        return vo;
    }

    private BizOrderRecord buildBizOrderRecord(CreateTransactionBody body){
        BizOrderRecord record = new BizOrderRecord();
        record.setInvoiceNo(body.getInvoice_no());
        record.setAmt(StringUtils.str2Num(body.getTotal_amount()));
        record.setOriginalInvoiceNo(body.getOriginal_invoice_no());
        record.setNum(StringUtils.str2Num(body.getTotal_quantity()).longValue());
        record.setShopCode(body.getStore_code());
        record.setChannelCode(body.getChannel_code());
        record.setEmployeeCode(body.getEmployee_code());
        record.setDiscount(StringUtils.str2Num(body.getTotal_discount()));
        record.setPayType(body.getTransaction_type_code());
        record.setPayTime(body.getTransaction_date());
        record.setCreateTime(body.getPurchase_date());
        record.setIsRefund("0");
        record.setDelFlag("0");
        if(record.getAmt().compareTo(BigDecimal.ZERO)==-1){
            record.setIsRefund("1");
        }
        record.setCreateBy("api");
        record.setUpdateBy("api");
        record.setUpdateTime(DateUtils.getNowDate());
        return record;
    }

    @Override
    @Transactional
    public VoidTransactionVo voidTransaction(VoidTransactionBody body) {
        VoidTransactionVo vo = new VoidTransactionVo(body.getOriginal_invoice_no());
        bizOrderRecordMapper.refund(body.getOriginal_invoice_no());
        return vo;
    }

    private BizOrderRecord buildBizRefundOrderRecord(CreateTransactionBody body){
        BizOrderRecord record = new BizOrderRecord();

        return record;
    }

    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    @Override
    public BizOrderRecordVo selectBizOrderRecordById(Long id)
    {
        return bizOrderRecordMapper.selectBizOrderRecordById(id);
    }

    /**
     * 查询订单明细列表
     * 
     * @param bizOrderRecord 订单明细
     * @return 订单明细
     */
    @Override
    public List<BizOrderRecordVo> selectBizOrderRecordList(BizOrderRecordVo bizOrderRecord)
    {
        return bizOrderRecordMapper.selectBizOrderRecordList(bizOrderRecord);
    }

    /**
     * 新增订单明细
     * 
     * @param bizOrderRecord 订单明细
     * @return 结果
     */
    @Override
    public int insertBizOrderRecord(BizOrderRecord bizOrderRecord)
    {
        bizOrderRecord.setCreateTime(DateUtils.getNowDate());
        return bizOrderRecordMapper.insertBizOrderRecord(bizOrderRecord);
    }

    /**
     * 修改订单明细
     * 
     * @param bizOrderRecord 订单明细
     * @return 结果
     */
    @Override
    public int updateBizOrderRecord(BizOrderRecord bizOrderRecord)
    {
        bizOrderRecord.setUpdateTime(DateUtils.getNowDate());
        return bizOrderRecordMapper.updateBizOrderRecord(bizOrderRecord);
    }

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键
     * @return 结果
     */
    @Override
    public int deleteBizOrderRecordByIds(Long[] ids)
    {
        return bizOrderRecordMapper.deleteBizOrderRecordByIds(ids);
    }

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    @Override
    public int deleteBizOrderRecordById(Long id)
    {
        return bizOrderRecordMapper.deleteBizOrderRecordById(id);
    }
}
