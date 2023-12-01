package com.micro.reima.service;

import com.micro.reima.model.admin.BizUserChildVo;
import com.micro.reima.model.bo.BizUserChildInfo;
import java.util.List;
import com.micro.reima.domain.BizUserChild;

/**
 * 用户孩子Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizUserChildService 
{

    public List<BizUserChild> selectBizUserChildsByUserId(Long userId);

    public void saveBizUserChildInfo(List<BizUserChildInfo> bos,Long userId);
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
     * 批量删除用户孩子
     * 
     * @param ids 需要删除的用户孩子主键集合
     * @return 结果
     */
    public int deleteBizUserChildByIds(Long[] ids);

    /**
     * 删除用户孩子信息
     * 
     * @param id 用户孩子主键
     * @return 结果
     */
    public int deleteBizUserChildById(Long id);
}
