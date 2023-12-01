package com.micro.reima.mapper;

import com.micro.reima.domain.BizExplain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明信息Mapper接口
 * 
 * @author micro
 * @date 2021-12-21
 */
public interface BizExplainMapper 
{
    public String selectByTitle(@Param("title") String title);
    /**
     * 查询说明信息
     * 
     * @param id 说明信息主键
     * @return 说明信息
     */
    public BizExplain selectBizExplainById(Long id);

    /**
     * 查询说明信息列表
     * 
     * @param bizExplain 说明信息
     * @return 说明信息集合
     */
    public List<BizExplain> selectBizExplainList(BizExplain bizExplain);

    /**
     * 新增说明信息
     * 
     * @param bizExplain 说明信息
     * @return 结果
     */
    public int insertBizExplain(BizExplain bizExplain);

    /**
     * 修改说明信息
     * 
     * @param bizExplain 说明信息
     * @return 结果
     */
    public int updateBizExplain(BizExplain bizExplain);

    /**
     * 删除说明信息
     * 
     * @param id 说明信息主键
     * @return 结果
     */
    public int deleteBizExplainById(Long id);

    /**
     * 批量删除说明信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizExplainByIds(Long[] ids);
}
