package com.micro.reima.service.impl;

import com.micro.reima.domain.BizExplain;
import com.micro.reima.mapper.BizExplainMapper;
import com.micro.reima.service.IBizExplainService;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明信息Service业务层处理
 * 
 * @author micro
 * @date 2021-12-21
 */
@Service
public class BizExplainServiceImpl implements IBizExplainService
{
    @Autowired
    private BizExplainMapper bizExplainMapper;

    @Override
    public String selectByTitle(String title) {
        return bizExplainMapper.selectByTitle(title);
    }

    /**
     * 查询说明信息
     * 
     * @param id 说明信息主键
     * @return 说明信息
     */
    @Override
    public BizExplain selectBizExplainById(Long id)
    {
        return bizExplainMapper.selectBizExplainById(id);
    }

    /**
     * 查询说明信息列表
     * 
     * @param bizExplain 说明信息
     * @return 说明信息
     */
    @Override
    public List<BizExplain> selectBizExplainList(BizExplain bizExplain)
    {
        return bizExplainMapper.selectBizExplainList(bizExplain);
    }

    /**
     * 新增说明信息
     * 
     * @param bizExplain 说明信息
     * @return 结果
     */
    @Override
    public int insertBizExplain(BizExplain bizExplain)
    {
        bizExplain.setCreateTime(DateUtils.getNowDate());
        return bizExplainMapper.insertBizExplain(bizExplain);
    }

    /**
     * 修改说明信息
     * 
     * @param bizExplain 说明信息
     * @return 结果
     */
    @Override
    public int updateBizExplain(BizExplain bizExplain)
    {
        bizExplain.setUpdateTime(DateUtils.getNowDate());
        return bizExplainMapper.updateBizExplain(bizExplain);
    }

    /**
     * 批量删除说明信息
     * 
     * @param ids 需要删除的说明信息主键
     * @return 结果
     */
    @Override
    public int deleteBizExplainByIds(Long[] ids)
    {
        return bizExplainMapper.deleteBizExplainByIds(ids);
    }

    /**
     * 删除说明信息信息
     * 
     * @param id 说明信息主键
     * @return 结果
     */
    @Override
    public int deleteBizExplainById(Long id)
    {
        return bizExplainMapper.deleteBizExplainById(id);
    }
}
