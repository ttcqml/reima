package com.micro.reima.mapper;

import com.micro.reima.model.admin.BizUserAccVo;
import java.math.BigDecimal;
import java.util.List;
import com.micro.reima.domain.BizUserAcc;
import org.apache.ibatis.annotations.Param;

/**
 * 积分账户Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizUserAccMapper 
{
    public BizUserAcc selectBizUserAccByUserId(Long userId);

    public List<Long> checkBizUserAcc();

    public int updateUserPoints(@Param("userId") Long userId, @Param("points") BigDecimal points);
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
     * 删除积分账户
     * 
     * @param id 积分账户主键
     * @return 结果
     */
    public int deleteBizUserAccById(Long id);

    /**
     * 批量删除积分账户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserAccByIds(Long[] ids);
}
