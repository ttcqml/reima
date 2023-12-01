package com.micro.reima.service;

import com.micro.reima.domain.BizPointChangeLog;

import java.util.List;

/**
 * 变更记录Service接口
 * 
 * @author micro
 * @date 2022-04-27
 */
public interface IBizPointChangeLogService 
{
    /**
     * 查询变更记录
     * 
     * @param id 变更记录主键
     * @return 变更记录
     */
    public BizPointChangeLog selectBizPointChangeLogById(Long id);

    /**
     * 查询变更记录列表
     * 
     * @param bizPointChangeLog 变更记录
     * @return 变更记录集合
     */
    public List<BizPointChangeLog> selectBizPointChangeLogList(BizPointChangeLog bizPointChangeLog);

    /**
     * 新增变更记录
     * 
     * @param bizPointChangeLog 变更记录
     * @return 结果
     */
    public int insertBizPointChangeLog(BizPointChangeLog bizPointChangeLog);

    /**
     * 修改变更记录
     * 
     * @param bizPointChangeLog 变更记录
     * @return 结果
     */
    public int updateBizPointChangeLog(BizPointChangeLog bizPointChangeLog);

    /**
     * 批量删除变更记录
     * 
     * @param ids 需要删除的变更记录主键集合
     * @return 结果
     */
    public int deleteBizPointChangeLogByIds(Long[] ids);

    /**
     * 删除变更记录信息
     * 
     * @param id 变更记录主键
     * @return 结果
     */
    public int deleteBizPointChangeLogById(Long id);
}
