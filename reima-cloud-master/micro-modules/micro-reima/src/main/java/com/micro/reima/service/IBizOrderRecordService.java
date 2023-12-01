package com.micro.reima.service;

import com.micro.reima.model.admin.BizOrderRecordVo;
import com.micro.reima.model.pos.CreateTransactionBody;
import com.micro.reima.model.pos.CreateTransactionVo;
import com.micro.reima.model.pos.VoidTransactionBody;
import com.micro.reima.model.pos.VoidTransactionVo;
import com.micro.reima.model.vo.UserAccRecordVo;
import com.micro.reima.model.vo.UserOrderRecordVo;
import java.util.List;
import com.micro.reima.domain.BizOrderRecord;

/**
 * 订单明细Service接口
 * 
 * @author micro
 * @date 2022-01-05
 */
public interface IBizOrderRecordService 
{
    public List<UserOrderRecordVo> selectUserOrderRecordVoByUserId(Long userId,String startTime,String endTime);

    CreateTransactionVo createTransaction(CreateTransactionBody body);

    VoidTransactionVo voidTransaction(VoidTransactionBody body);

    /**
     * 查询订单明细
     * 
     * @param id 订单明细主键
     * @return 订单明细
     */
    public BizOrderRecordVo selectBizOrderRecordById(Long id);

    /**
     * 查询订单明细列表
     * 
     * @param bizOrderRecord 订单明细
     * @return 订单明细集合
     */
    public List<BizOrderRecordVo> selectBizOrderRecordList(BizOrderRecordVo bizOrderRecord);

    /**
     * 新增订单明细
     * 
     * @param bizOrderRecord 订单明细
     * @return 结果
     */
    public int insertBizOrderRecord(BizOrderRecord bizOrderRecord);

    /**
     * 修改订单明细
     * 
     * @param bizOrderRecord 订单明细
     * @return 结果
     */
    public int updateBizOrderRecord(BizOrderRecord bizOrderRecord);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键集合
     * @return 结果
     */
    public int deleteBizOrderRecordByIds(Long[] ids);

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteBizOrderRecordById(Long id);
}
