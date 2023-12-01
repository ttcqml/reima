package com.micro.reima.service.impl;

import com.micro.reima.domain.BizMedia;
import com.micro.reima.mapper.BizMediaMapper;
import com.micro.reima.model.vo.SimpleMediaVo;
import com.micro.reima.service.IBizMediaService;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 资讯媒体Service业务层处理
 * 
 * @author micro
 * @date 2021-12-21
 */
@Service
public class BizMediaServiceImpl implements IBizMediaService
{
    @Autowired
    private BizMediaMapper bizMediaMapper;

    @Override
    public List<SimpleMediaVo> appList() {
        return bizMediaMapper.appList();
    }

    /**
     * 查询资讯媒体
     * 
     * @param id 资讯媒体主键
     * @return 资讯媒体
     */
    @Override
    public BizMedia selectBizMediaById(Long id)
    {
        return bizMediaMapper.selectBizMediaById(id);
    }

    /**
     * 查询资讯媒体列表
     * 
     * @param bizMedia 资讯媒体
     * @return 资讯媒体
     */
    @Override
    public List<BizMedia> selectBizMediaList(BizMedia bizMedia)
    {
        return bizMediaMapper.selectBizMediaList(bizMedia);
    }

    /**
     * 新增资讯媒体
     * 
     * @param bizMedia 资讯媒体
     * @return 结果
     */
    @Override
    public int insertBizMedia(BizMedia bizMedia)
    {
        bizMedia.setCreateTime(DateUtils.getNowDate());
        return bizMediaMapper.insertBizMedia(bizMedia);
    }

    /**
     * 修改资讯媒体
     * 
     * @param bizMedia 资讯媒体
     * @return 结果
     */
    @Override
    public int updateBizMedia(BizMedia bizMedia)
    {
        bizMedia.setUpdateTime(DateUtils.getNowDate());
        return bizMediaMapper.updateBizMedia(bizMedia);
    }

    /**
     * 批量删除资讯媒体
     * 
     * @param ids 需要删除的资讯媒体主键
     * @return 结果
     */
    @Override
    public int deleteBizMediaByIds(Long[] ids)
    {
        return bizMediaMapper.deleteBizMediaByIds(ids);
    }

    /**
     * 删除资讯媒体信息
     * 
     * @param id 资讯媒体主键
     * @return 结果
     */
    @Override
    public int deleteBizMediaById(Long id)
    {
        return bizMediaMapper.deleteBizMediaById(id);
    }
}
