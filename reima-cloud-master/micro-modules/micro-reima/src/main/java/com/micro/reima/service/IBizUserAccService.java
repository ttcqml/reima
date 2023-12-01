package com.micro.reima.service;

import com.micro.reima.model.admin.BizUserAccVo;
import java.math.BigDecimal;
import java.util.List;
import com.micro.reima.domain.BizUserAcc;

/**
 * 积分账户Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizUserAccService 
{

    public void editBalance(Long userId,BigDecimal amt,String remark);

    public BizUserAcc selectBizUserAccByUserId(Long userId);

    public void addUserAccBalance(Long userId,BigDecimal amt,String memo,boolean canRepeat);

    public void subUserAccBalance(Long userId,BigDecimal amt,String memo);
    /**
     * 查询积分账户
     * 
     * @param id 积分账户主键
     * @return 积分账户
     */
    public BizUserAccVo selectBizUserAccById(Long id);

    /**
     * 查询积分账户列表
     * 
     * @param bizUserAcc 积分账户
     * @return 积分账户集合
     */
    public List<BizUserAccVo> selectBizUserAccList(BizUserAccVo bizUserAcc);

    /**
     * 新增积分账户
     * 
     * @param bizUserAcc 积分账户
     * @return 结果
     */
    public int insertBizUserAcc(BizUserAcc bizUserAcc);

    /**
     * 修改积分账户
     * 
     * @param bizUserAcc 积分账户
     * @return 结果
     */
    public int updateBizUserAcc(BizUserAcc bizUserAcc);

    /**
     * 批量删除积分账户
     * 
     * @param ids 需要删除的积分账户主键集合
     * @return 结果
     */
    public int deleteBizUserAccByIds(Long[] ids);

    /**
     * 删除积分账户信息
     * 
     * @param id 积分账户主键
     * @return 结果
     */
    public int deleteBizUserAccById(Long id);

    public int correctUserAcc(Long userId,BigDecimal amt);
}
