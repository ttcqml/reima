package com.micro.reima.service.impl;

import java.util.List;
import com.micro.common.core.utils.DateUtils;
import com.micro.reima.model.vo.SimpleRegionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizRegionMapper;
import com.micro.reima.domain.BizRegion;
import com.micro.reima.service.IBizRegionService;

/**
 * 行政区域Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizRegionServiceImpl implements IBizRegionService 
{
    @Autowired
    private BizRegionMapper bizRegionMapper;

    @Override
    public List<SimpleRegionVo> appList(Long pid) {
        return bizRegionMapper.appList(pid);
    }

    /**
     * 查询行政区域
     * 
     * @param id 行政区域主键
     * @return 行政区域
     */
    @Override
    public BizRegion selectBizRegionById(Long id)
    {
        return bizRegionMapper.selectBizRegionById(id);
    }

    /**
     * 查询行政区域列表
     * 
     * @param bizRegion 行政区域
     * @return 行政区域
     */
    @Override
    public List<BizRegion> selectBizRegionList(BizRegion bizRegion)
    {
        return bizRegionMapper.selectBizRegionList(bizRegion);
    }

    /**
     * 新增行政区域
     * 
     * @param bizRegion 行政区域
     * @return 结果
     */
    @Override
    public int insertBizRegion(BizRegion bizRegion)
    {
        bizRegion.setCreateTime(DateUtils.getNowDate());
        return bizRegionMapper.insertBizRegion(bizRegion);
    }

    /**
     * 修改行政区域
     * 
     * @param bizRegion 行政区域
     * @return 结果
     */
    @Override
    public int updateBizRegion(BizRegion bizRegion)
    {
        return bizRegionMapper.updateBizRegion(bizRegion);
    }

    /**
     * 批量删除行政区域
     * 
     * @param ids 需要删除的行政区域主键
     * @return 结果
     */
    @Override
    public int deleteBizRegionByIds(Long[] ids)
    {
        return bizRegionMapper.deleteBizRegionByIds(ids);
    }

    /**
     * 删除行政区域信息
     * 
     * @param id 行政区域主键
     * @return 结果
     */
    @Override
    public int deleteBizRegionById(Long id)
    {
        return bizRegionMapper.deleteBizRegionById(id);
    }
}
