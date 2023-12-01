package com.micro.reima.mapper;

import com.micro.reima.model.admin.BizUserChildVo;
import java.util.List;
import com.micro.reima.domain.BizUserChild;
import org.apache.ibatis.annotations.Param;

/**
 * 用户孩子Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizUserChildMapper 
{
    public List<BizUserChild> selectBizUserChildsByUserId(@Param("userId") Long userId);
    /**
     * 查询用户孩子
     * 
     * @param id 用户孩子主键
     * @return 用户孩子
     */
    public BizUserChildVo selectBizUserChildById(Long id);

    /**
     * 查询用户孩子列表
     * 
     * @param bizUserChild 用户孩子
     * @return 用户孩子集合
     */
    public List<BizUserChildVo> selectBizUserChildList(BizUserChildVo bizUserChild);

    /**
     * 新增用户孩子
     * 
     * @param bizUserChild 用户孩子
     * @return 结果
     */
    public int insertBizUserChild(BizUserChild bizUserChild);

    /**
     * 修改用户孩子
     * 
     * @param bizUserChild 用户孩子
     * @return 结果
     */
    public int updateBizUserChild(BizUserChild bizUserChild);

    /**
     * 删除用户孩子
     * 
     * @param id 用户孩子主键
     * @return 结果
     */
    public int deleteBizUserChildById(Long id);

    /**
     * 批量删除用户孩子
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserChildByIds(Long[] ids);
}
