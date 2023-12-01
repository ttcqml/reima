package com.micro.reima.mapper;

import com.micro.reima.model.admin.BizExchangeRecordVo;
import com.micro.reima.model.vo.ExCouponVo;
import com.micro.reima.model.vo.ExProductVo;
import java.util.List;
import com.micro.reima.domain.BizExchangeRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 兑换历史Mapper接口
 * 
 * @author micro
 * @date 2021-12-25
 */
public interface BizExchangeRecordMapper 
{
    public List<ExProductVo> exProductList(Long userId);

    public List<ExCouponVo> exCouponList(Long userId);

    public int updateExpressInfo(@Param("orderSn") String orderSn, @Param("express")String express, @Param("expressNum")String expressNum);
    /**
     * 查询兑换历史
     * 
     * @param id 兑换历史主键
     * @return 兑换历史
     */
    public BizExchangeRecordVo selectBizExchangeRecordById(Long id);

    /**
     * 查询兑换历史列表
     * 
     * @param bizExchangeRecord 兑换历史
     * @return 兑换历史集合
     */
    public List<BizExchangeRecordVo> selectBizExchangeRecordList(BizExchangeRecordVo bizExchangeRecord);

    /**
     * 新增兑换历史
     * 
     * @param bizExchangeRecord 兑换历史
     * @return 结果
     */
    public int insertBizExchangeRecord(BizExchangeRecord bizExchangeRecord);

    /**
     * 修改兑换历史
     * 
     * @param bizExchangeRecord 兑换历史
     * @return 结果
     */
    public int updateBizExchangeRecord(BizExchangeRecord bizExchangeRecord);

    /**
     * 删除兑换历史
     * 
     * @param id 兑换历史主键
     * @return 结果
     */
    public int deleteBizExchangeRecordById(Long id);

    /**
     * 批量删除兑换历史
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizExchangeRecordByIds(Long[] ids);
}
