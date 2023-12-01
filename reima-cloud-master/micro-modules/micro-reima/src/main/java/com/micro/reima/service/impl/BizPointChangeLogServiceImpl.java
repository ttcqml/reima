package com.micro.reima.service.impl;

import java.util.List;
import com.micro.common.core.utils.DateUtils;
import com.micro.reima.domain.BizPointChangeLog;
import com.micro.reima.mapper.BizPointChangeLogMapper;
import com.micro.reima.service.IBizPointChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 变更记录Service业务层处理
 * 
 * @author micro
 * @date 2022-04-27
 */
@Service
public class BizPointChangeLogServiceImpl implements IBizPointChangeLogService
{
    @Autowired
    private BizPointChangeLogMapper bizPointChangeLogMapper;

    /**
     * 查询变更记录
     * 
     * @param id 变更记录主键
     * @return 变更记录
     */
    @Override
    public BizPointChangeLog selectBizPointChangeLogById(Long id)
    {
        return bizPointChangeLogMapper.selectBizPointChangeLogById(id);
    }

    /**
     * 查询变更记录列表
     * 
     * @param bizPointChangeLog 变更记录
     * @return 变更记录
     */
    @Override
    public List<BizPointChangeLog> selectBizPointChangeLogList(BizPointChangeLog bizPointChangeLog)
    {
        return bizPointChangeLogMapper.selectBizPointChangeLogList(bizPointChangeLog);
    }

    /**
     * 新增变更记录
     * 
     * @param bizPointChangeLog 变更记录
     * @return 结果
     */
    @Override
    public int insertBizPointChangeLog(BizPointChangeLog bizPointChangeLog)
    {
        bizPointChangeLog.setCreateTime(DateUtils.getNowDate());
        return bizPointChangeLogMapper.insertBizPointChangeLog(bizPointChangeLog);
    }

    /**
     * 修改变更记录
     * 
     * @param bizPointChangeLog 变更记录
     * @return 结果
     */
    @Override
    public int updateBizPointChangeLog(BizPointChangeLog bizPointChangeLog)
    {
        bizPointChangeLog.setUpdateTime(DateUtils.getNowDate());
        return bizPointChangeLogMapper.updateBizPointChangeLog(bizPointChangeLog);
    }

    /**
     * 批量删除变更记录
     * 
     * @param ids 需要删除的变更记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPointChangeLogByIds(Long[] ids)
    {
        return bizPointChangeLogMapper.deleteBizPointChangeLogByIds(ids);
    }

    /**
     * 删除变更记录信息
     * 
     * @param id 变更记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPointChangeLogById(Long id)
    {
        return bizPointChangeLogMapper.deleteBizPointChangeLogById(id);
    }
}
